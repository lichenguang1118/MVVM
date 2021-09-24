package com.fx.mvvm.di

import com.fx.mvvm.data.network.LoginApi
import com.fx.mvvm.data.network.RemoteDataSource
import com.fx.mvvm.data.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**

 * @Author : chenguang

 * @Time : On 2021/9/23 0023 17:28

 * @Description : AppModule

 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource()
    }

    @Provides
    fun provideLoginRepository(loginApi: LoginApi): LoginRepository {
        return LoginRepository(loginApi)
    }
}