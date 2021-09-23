package com.fx.mvvm.data.network

import android.content.Context
import com.fx.mvvm.data.responses.BaseResponse
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Singleton

/**

 * @Author : chenguang

 * @Time : On 2021/9/22 0022 14:31

 * @Description : Api

 */
interface LoginApi {

    @POST(Url.CHECK_USER)
    suspend fun checkUser(@Query("sfzh") sfzh: String): BaseResponse


    @Singleton
    @Provides
    fun provideApi(
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): LoginApi {
        return remoteDataSource.buildApi(LoginApi::class.java, context)
    }
}