package com.hakanemik.easyfood.di

import com.hakanemik.easyfood.data.datasource.FoodsDataSource
import com.hakanemik.easyfood.data.repo.FoodsRepository
import com.hakanemik.easyfood.retrofit.ApiUtils
import com.hakanemik.easyfood.retrofit.FoodsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideFoodsRepository(foodsDataSource: FoodsDataSource): FoodsRepository {
        return FoodsRepository(foodsDataSource)
    }

    @Provides
    @Singleton
    fun provideFoodsDataSource(foodsDao: FoodsDao): FoodsDataSource {
        return FoodsDataSource(foodsDao)
    }

    @Provides
    @Singleton
    fun provideFoodsDao(): FoodsDao {
        return ApiUtils.getFoodsDao()
    }
}