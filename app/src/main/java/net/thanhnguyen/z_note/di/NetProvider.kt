package net.thanhnguyen.z_note.di

import net.thanhnguyen.z_note.data.api.IService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun baseURL() = "https://tmdb.com"
fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder().build()
fun provideRetrofit(http: OkHttpClient): Retrofit = Retrofit.Builder().baseUrl(baseURL())
    .addConverterFactory(GsonConverterFactory.create())
    .client(http).build()

fun provideIService(retrofit: Retrofit): IService = retrofit.create(IService::class.java)