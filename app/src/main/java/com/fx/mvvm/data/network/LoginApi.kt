package com.fx.mvvm.data.network

import com.fx.mvvm.data.responses.BaseResponse
import com.fx.mvvm.data.responses.TokenResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

/**

 * @Author : chenguang

 * @Time : On 2021/9/22 0022 14:31

 * @Description : Api

 */
interface LoginApi {

    @POST(Url.CHECK_USER)
    suspend fun checkUser(@Query("sfzh") sfzh: String): BaseResponse<Boolean>

    @POST(Url.GET_USER_TOKEN)
    suspend fun getUserToken(@Body body: RequestBody) :BaseResponse<TokenResponse>

}