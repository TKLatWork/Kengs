// pages/timer/timer.js

var app = getApp();
var plugin = requirePlugin("WechatSI");
var timer = require('../../service/TimerService.js')
var reader = require('../../service/ReadService.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    //计时规则配置
    vm:{
      time: 0,//秒表时间
      round: 1,//轮数
      running: false,
      notRunning: true,
      standBying: true,
    },
    config:{

    },
    intervalID:null,
  },

  start: function(e){
    console.log("start")
    wx.setStorageSync('timer', this.data.config)
    wx.setKeepScreenOn({
      keepScreenOn: true
    });
    timer.start(this);
  },

  stop: function (e) {
    console.log("stop")
    wx.setKeepScreenOn({
      keepScreenOn: false
    })
    timer.stop(this);
  },

  setConfigTrain: function(){
    this.setConfigAll(20, 5, 10, 9);
  },

  setConfigMatch: function () {
    this.setConfigAll(240, 40, 10, 1);
  },

  setConfigAll: function (totalTime, notifyPeriod, standByTime, roundNum) {
    console.log("setConfigAll:", totalTime, notifyPeriod, standByTime, roundNum);
    var config = this.data.config;
    config.totalTime = totalTime;
    config.notifyPeriod = notifyPeriod;
    config.standByTime = standByTime;
    config.roundNum = roundNum;
    this.setData({ config: config });
  },

  toValidVal: function(val){
    var valInt = Number.parseInt(val.trim());
    if (Number.isNaN(valInt) || valInt < 0) {
      valInt = 0;
    }
    return valInt;
  },

  setConfigNumVal: function(e){
    if (this.data.vm.running) {
      console.log("Can't change", valInt);
      return;
    }
    
    var val = e.detail.value;
    var target = e.target.dataset.target;
    var config = this.data.config;
    console.log("set:" + target, val);
    //format check
    var valInt = this.toValidVal(val); 

    //set
    switch(target){
      case 'totalTime': config.totalTime = valInt; break;
      case 'notifyPeriod': config.notifyPeriod = valInt; break;
      case 'standByTime': config.standByTime = valInt; break;
      case 'roundNum': config.roundNum = valInt; break;
    }
    this.setData({ config: config});
  },

  readText: function(text){
    reader.readText(plugin, this.playAudio, text);
  },

  playAudio: function(src){
    var bam = wx.getBackgroundAudioManager();
    bam.title = 'Timer';
    bam.src = src;
    return bam;
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //
    console.log("onLoad", app.globalData)
    this.setData({config: app.globalData.timerConfig})
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    this.stop()
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})