package com.ivanebernal.githubdemokotlin.dagger

import com.ivanebernal.githubdemokotlin.GithubClient
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by rraya on 10/20/17.
 */

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.github.com")
                .build()

    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): GithubClient {
        return retrofit.create(GithubClient::class.java)
    }

}