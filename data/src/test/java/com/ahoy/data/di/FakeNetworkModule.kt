package com.ahoy.data.di

import android.app.Application
import com.ahoy.data.cloud.FakeCloudRepository
import com.ahoy.data.repository.BaseCloudRepository
import com.ahoy.data.restful.FakeApiService
import com.ahoy.data.util.JsonUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object FakeNetworkModule {

    @Singleton
    @Provides
    fun provideJsonUtil(application: Application): JsonUtil {
        return JsonUtil(application)
    }

    @Singleton
    @Provides
    fun provideCloudRepository(apIs: FakeApiService): BaseCloudRepository {
        return FakeCloudRepository(apIs)
    }
}














