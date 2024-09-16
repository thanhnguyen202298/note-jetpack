package net.thanhnguyen.z_note.di

import net.thanhnguyen.z_note.data.api.IService
import net.thanhnguyen.z_note.data.model.NoteItem
import okhttp3.OkHttpClient
import okio.Okio
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun baseURL() = ""
fun provideOkHttp() = OkHttpClient.Builder().build()
fun provideRetrofit(http: OkHttpClient) = Retrofit.Builder().baseUrl(baseURL())
    .addConverterFactory(GsonConverterFactory.create())
    .client(http).build()

fun provideIService(retrofit: Retrofit) = retrofit.create(IService::class.java)