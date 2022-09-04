package com.ahoy.weather.di.module

import com.ahoy.data.repository.AppRepoImp
import com.ahoy.data.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindAppRepository(
        appRepoImp: AppRepoImp
    ): AppRepository


}