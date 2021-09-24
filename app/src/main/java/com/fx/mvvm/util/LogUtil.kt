package com.fx.mvvm.util

import android.util.Log

object LogUtil {
    private const val TAG = "ZaiJiaoTong"
    var isDebug = true
    fun i(s: String?) {
        if (isDebug) {
            Log.i(TAG, s!!)
        }
    }

    fun i(TAG: String?, s: String?) {
        if (isDebug) {
            Log.i(TAG, s!!)
        }
    }

    fun d(s: String?) {
        if (isDebug) {
            Log.d(TAG, s!!)
        }
    }

    fun d(TAG: String?, s: String?) {
        if (isDebug) {
            Log.d(TAG, s!!)
        }
    }

    fun e(s: String?) {
        if (isDebug) {
            Log.e(TAG, s!!)
        }
    }

    fun e(TAG: String?, s: String?) {
        if (isDebug) {
            Log.e(TAG, s!!)
        }
    }
}