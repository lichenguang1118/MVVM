package com.fx.mvvm.repository

import com.fx.mvvm.network.Api

/**

 * @Author : chenguang

 * @Time : On 2021/9/22 0022 15:20

 * @Description : LoginRepository

 */
class LoginRepository(
    private val api: Api
) : BaseRepository() {

    suspend fun checkUser(
        sfzh: String
    ) = safeApiCall {
        api.checkUser(sfzh)
    }

}