package com.fx.mvvm.network

import com.fx.mvvm.responses.BaseResponse
import retrofit2.http.POST
import retrofit2.http.Query

/**

 * @Author : chenguang

 * @Time : On 2021/9/22 0022 14:31

 * @Description : Api

 */
interface Api {

    @POST(Url.CHECK_USER)
    suspend fun checkUser(@Query("sfzh") sfzh: String): BaseResponse

}