package com.fx.mvvm.data.repository

import com.fx.mvvm.data.network.HomeApi
import okhttp3.RequestBody
import javax.inject.Inject

/**

 * @Author : chenguang

 * @Time : On 2021/9/26 0026 11:37

 * @Description : HomeRepository

 */
class HomeRepository @Inject constructor(
    private val api: HomeApi
) : BaseRepository() {

    suspend fun getUserInfo(requestBody: RequestBody) =
        safeApiCall { api.getUserInfo(requestBody) }

    suspend fun getBannerList(requestBody: RequestBody) =
        safeApiCall { api.getBannerList(requestBody) }

    suspend fun getPageCallPolice(requestBody: RequestBody) =
        safeApiCall { api.getPageCallPolice(requestBody) }
}