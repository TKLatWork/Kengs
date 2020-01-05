//app.js
App({
  onLaunch: function () {
    // 展示本地存储能力
    var timer = wx.getStorageSync('timer') || {}

    if(!timer.timerConfig){
      timer.timerConfig = {
        totalTime: 240,//总时长
        notifyPeriod: 40,//提示间隔
        standByTime: 10,//每轮的间隔等待时间
        roundNum: 2//轮数
      }
      wx.setStorageSync('timer', timer)
    }

    this.globalData = {timerConfig: timer.timerConfig}

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
  },
  globalData: {
    timerConfig: {
      totalTime: 45,//总时长
      notifyPeriod: 40,//提示间隔
      standByTime: 10,//每轮的间隔等待时间
      roundNum: 3//轮数
    }
  }
})