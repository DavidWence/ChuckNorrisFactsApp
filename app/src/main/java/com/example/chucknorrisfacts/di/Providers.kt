package com.example.chucknorrisfacts.di

import com.example.chucknorrisfacts.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


fun <T>provideApiService(retrofit: Retrofit, clazz: Class<T>): T = retrofit.create(clazz)

fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
    .client(
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build())
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(
        MoshiConverterFactory.create(
            Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()))
    .build()