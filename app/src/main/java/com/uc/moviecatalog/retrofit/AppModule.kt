package com.uc.moviecatalog.retrofit

import com.uc.moviecatalog.helper.const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getRetrofitServiceInstance(retrofit: Retrofit):
            EndPointAPI{
                return retrofit.create(EndPointAPI::class.java)
            }

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(const.BASE_uri)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}