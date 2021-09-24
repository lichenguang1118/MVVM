package com.fx.mvvm.data.network

import okhttp3.ResponseBody

/**

 * @Author : chenguang

 * @Time : On 2021/9/22 0022 14:48

 * @Description : Resource

 */
sealed class Resource<out T> {

    data class Success<out T>(val value: T) : Resource<T>()

    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()

}