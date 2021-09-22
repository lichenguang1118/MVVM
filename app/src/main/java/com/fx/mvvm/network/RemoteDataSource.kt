package com.fx.mvvm.network

import com.fx.mvvm.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**

 * @Author : chenguang

 * @Time : On 2021/9/22 0022 15:06

 * @Description : RemoteDataSource

 */
class RemoteDataSource {

    companion object{
        private const val BASE_URL = "http://dev.hijz.faxuanyun.com"
    }

    fun <Api> buildApi(
        api : Class<Api>
    ) :Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().also { client ->
                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}