package com.fx.mvvm.data.repository

import com.fx.mvvm.data.network.LoginApi
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.RequestBody
import javax.inject.Inject

/**

 * @Author : chenguang

 * @Time : On 2021/9/22 0022 15:20

 * @Description : LoginRepository

 */
class LoginRepository @Inject constructor(
    private val api: LoginApi
) : BaseRepository() {

    suspend fun checkUser(
        sfzh: String
    ) = safeApiCall {
        api.checkUser(sfzh)
    }

    suspend fun getUserToken(
        requestBody: RequestBody
    ) = safeApiCall {
        api.getUserToken(requestBody)
    }

}