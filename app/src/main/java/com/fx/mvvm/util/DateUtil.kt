package com.fx.mvvm.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/**

 * @Author : chenguang

 * @Time : On 2021/9/27 0027 16:29

 * @Description : DateUtil

 */
object DateUtil {

    @SuppressLint("SimpleDateFormat")
    fun convertTimeStamp(timeStamp: Long): String {
        return SimpleDateFormat("HH:mm").format(timeStamp)
    }

}