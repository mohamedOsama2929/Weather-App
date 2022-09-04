package com.ahoy.weather.di.module

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object GlobalSearchActivityModule {

    @Provides
    @ActivityScoped
    fun provideSearchQueryUpdated() = MutableLiveData<String>()

    @Provides
    @ActivityScoped
    fun provideSearchResultNotEmpty() = MutableLiveData<Boolean>()
}