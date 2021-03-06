package com.saba.core.di.module

import com.google.gson.GsonBuilder
import com.saba.core.di.CoreScope
import com.saba.core.network.ErrorInterceptor
import com.saba.core.network.services.BookService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @CoreScope
    @Provides
    fun provideGsonConverter(): GsonConverterFactory {
        val gson = GsonBuilder()
            .create()
        return GsonConverterFactory.create(gson)
    }

    @CoreScope
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
            })
            .addInterceptor(ErrorInterceptor())

        return builder.build()
    }

    @CoreScope
    @Provides
    fun provideBooksApiRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        httpClient: OkHttpClient,
        baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(httpClient)
            .build()
    }

    @CoreScope
    @Provides
    fun provideBookService(retrofit: Retrofit): BookService =
        retrofit.create(BookService::class.java)


    private const val TIME_OUT_SECONDS = 30L

}

