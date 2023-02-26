package com.richmondprojects.acegameapp.data.remote.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiBuilder @Inject constructor() {

    fun <Api> builder(api: Class<Api>): Api {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.freetogame.com/api/").client(getClient())
            .build().create(api)
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("X-Requested-With", "XMLHttpRequest")
                        .addHeader("content-type", "application/json")
                }.build())
            }.also { client ->
                client.connectTimeout(1L, TimeUnit.MINUTES)
                    .readTimeout(1L, TimeUnit.MINUTES)
                    .writeTimeout(15L, TimeUnit.SECONDS)
            }.build()
    }
}