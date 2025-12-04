package com.samir.jetpackcomposemvvmclean.di

import android.content.Context
import com.samir.jetpackcomposemvvmclean.data.service.CatService
import com.samir.jetpackcomposemvvmclean.network.HeaderInterceptor
import com.samir.jetpackcomposemvvmclean.network.NetworkConnectionInterceptor
import com.samir.jetpackcomposemvvmclean.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideHTTPClient(@ApplicationContext context: Context): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().addInterceptor(HeaderInterceptor())
            .addInterceptor(NetworkConnectionInterceptor(context))
            .addInterceptor(loggingInterceptor).writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val url = Constants.baseUrl

        return Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideCatsService(retrofit: Retrofit): CatService {
        return retrofit.create(CatService::class.java)
    }
}