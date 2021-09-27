package com.fx.mvvm.data.responses

/**

 * @Author : chenguang

 * @Time : On 2021/9/27 0027 14:53

 * @Description : CallPoliceResponse

 */
data class CallPoliceResponse(
    val bjTime: Long,
    val bjsj: String,
    val bjzt: Int,
    val id: String,
    val sipid: String,
    var content: String,
    var timeString: String
)
