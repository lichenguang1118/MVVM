package com.fx.mvvm.data.repository

import com.fx.mvvm.data.network.LoginApi

/**

 * @Author : chenguang

 * @Time : On 2021/9/22 0022 15:20

 * @Description : LoginRepository

 */
class LoginRepository(
    private val api: LoginApi
) : BaseRepository() {

    suspend fun checkUser(
        sfzh: String
    ) = safeApiCall {
        api.checkUser(sfzh)
    }

}