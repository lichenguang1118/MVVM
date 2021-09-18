package com.fx.mvvm.application

import android.app.Application
import com.fx.common.util.LogUtil
import com.fx.mvvm.BuildConfig

/**

 * @Author : chenguang

 * @Time : On 2021/9/18 0018 17:09

 * @Description : BaseApplication

 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LogUtil.isDebug = BuildConfig.DEBUG
    }
}