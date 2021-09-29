package com.fx.mvvm.data.network

import com.fx.mvvm.data.responses.*
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**

 * @Author : chenguang

 * @Time : On 2021/9/22 0022 14:31

 * @Description : Api

 */
interface HomeApi {

    @POST(Url.GET_USER_INFO)
    suspend fun getUserInfo(@Body body: RequestBody): BaseResponse<UserInfoResponse>

    @POST(Url.GET_BANNER_LIST)
    suspend fun getBannerList(@Body body: RequestBody): BaseResponse<List<BannerResponse>>

    @POST(Url.GET_CALL_POLICE)
    suspend fun getPageCallPolice(@Body body: RequestBody): BaseResponse<List<CallPoliceResponse>>

    @POST(Url.GET_PAGE_MESSAGE)
    suspend fun getPageMessage(@Body body: RequestBody): BaseResponse<List<NotificationResponse>>
}