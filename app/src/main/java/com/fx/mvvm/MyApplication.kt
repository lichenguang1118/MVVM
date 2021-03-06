package com.fx.mvvm

import android.app.Application
import com.fx.mvvm.util.LogUtil
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp

/**

 * @Author : chenguang

 * @Time : On 2021/9/18 0018 17:09

 * @Description : BaseApplication

 */
@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
        LogUtil.isDebug = BuildConfig.DEBUG
    }
}