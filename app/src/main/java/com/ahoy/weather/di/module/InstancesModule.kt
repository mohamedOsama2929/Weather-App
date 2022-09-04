package com.ahoy.weather.di.module

import androidx.lifecycle.MutableLiveData
import com.ahoy.core.utils.Event
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InstancesModule {

    @Provides
    @Singleton
    fun provideIsFavUpdated() = MutableLiveData<Event<Boolean>>()
}