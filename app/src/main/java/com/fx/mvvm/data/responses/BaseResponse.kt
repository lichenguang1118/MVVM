package com.fx.mvvm.data.responses

/**

 * @Author : chenguang

 * @Time : On 2021/9/22 0022 14:55

 * @Description : BaseResponse

 */
data class BaseResponse(
    val code: Int,
    val msg: String,
    val data: Any
)