package com.ahoy.data.di.local

import android.content.Context
import androidx.room.Room
import com.ahoy.data.room.FavoriteCitiesDao
import com.ahoy.data.room.MainDatabase
import com.ahoy.data.source.local.ILocalDataSource
import com.ahoy.data.source.local.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {


    @Singleton
    @Provides
    fun provideMainDb(@ApplicationContext context: Context): MainDatabase {
        return Room
            .databaseBuilder(
                context,
                MainDatabase::class.java,
                MainDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideILocalDataSource(localDataSource: LocalDataSource): ILocalDataSource {
        return localDataSource
    }

    @Singleton
    @Provides
    fun provideFavirouteCitiesDAO(blogDatabase: MainDatabase): FavoriteCitiesDao {
        return blogDatabase.favoriteCitiesDao()
    }


//    @Singleton
//    @Provides
//    fun provideBlogDaoService(
//        mainDao: MainDao
//    ):BlogDaoService{
//        return BlogDaoServiceImpl(blogDao)
//    }
//
//    @Singleton
//    @Provides
//    fun provideCacheDataSource(
//        blogDaoService: BlogDaoService,
//        cacheMapper: CacheMapper
//    ): CacheDataSource{
//        return CacheDataSourceImpl(blogDaoService, cacheMapper)
//    }
}

























