/**
 * 语音合成，播放
 */

var local = {
  "15秒" : "/res/15s.mp3",
  "10秒": "/res/10s.mp3",
  "5秒": "/res/5s.mp3",
  //..
  "200秒": "/res/200s.mp3",
  "160秒": "/res/160s.mp3",
  "120秒": "/res/120s.mp3",
  "80秒": "/res/80s.mp3",
  "40秒": "/res/40s.mp3"
};

function readText(plugin, playFun, text) {
  if(checkLocal(playFun, text)){
    return;
  }
  readTextApi(plugin, playFun, text);
}

function checkLocal(playFun, text){
  if(local[text]){
    console.log("Local match:" + text);
    playFun(local[text]);
    return true;
  } else {
    return false;
  }
}

function readTextApi(plugin, playFun, text) {
  var self = this;
  plugin.textToSpeech({
    lang: "zh_CN",
    tts: true,
    content: text,
    success: function (res) {
      console.log("succ tts", res.filename)
      playFun(res.filename);
    },
    fail: function (res) {
      console.log("fail tts", res)
    }
  })
}


module.exports.readText = readText