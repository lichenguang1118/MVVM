package com.fx.mvvm

import android.app.Application
import com.fx.common.util.LogUtil

/**

 * @Author : chenguang

 * @Time : On 2021/9/18 0018 17:09

 * @Description : BaseApplication

 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LogUtil.isDebug = BuildConfig.DEBUG
    }
}