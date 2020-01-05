/**
 * 负责进行计时的代码
 */

function start(page) {
  var vm = page.data.vm;
  vm.running = true;
  vm.notRunning = false;

  //setdata round0 -> round1
  vm.time = 0;
  vm.round = 0;
  vm.standBying = false;

  page.setData({ vm: vm});
  //start
  page.data.intervalID = setInterval(timerLoop, 1000, page);
}

function stop(page) {
  if (page.data.intervalID) {
    clearInterval(page.data.intervalID);
    page.data.intervalID = null;
  }
  page.data.vm.running = false;
  page.data.vm.notRunning = true;
  page.setData({vm: page.data.vm, intervalID: page.data.intervalID});
}

/**
 * self = page对象
 */
function timerLoop(self) {
  console.log("loop")
  //data update
  self.data.vm.time--;
  var time = self.data.vm.time;
  var round = self.data.vm.round;
  var config = self.data.config;
  var period = config.notifyPeriod;
  if (time < 0) {
    if (self.data.vm.standBying) {
      //switch to round
      self.data.vm.time = config.totalTime;
      self.data.vm.standBying = false;
      self.playAudio("/res/start.mp3")
    } else if (round < config.roundNum) {
      //next round，standby
      self.data.vm.round++;
      self.data.vm.time = config.standByTime;
      self.data.vm.standBying = true;
      if (config.standByTime > 0) {
        self.playAudio("/res/standby.mp3")
      }
    } else {
      //ended
      self.data.vm.time = 0;
      self.playAudio("/res/end.mp3")
      self.stop();
    }
  } else if (
    !self.data.vm.standBying
    && time > 0
    && ((time % period) == 0)
  ) {
    //notice
    self.readText(time + "秒");
  }
  //set
  self.setData({ vm: self.data.vm });
  //sound
}


module.exports.timerLoop = timerLoop
module.exports.start = start
module.exports.stop = stop