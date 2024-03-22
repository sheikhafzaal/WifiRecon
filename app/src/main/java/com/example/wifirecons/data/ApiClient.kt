package com.example.wifirecons.data

import com.example.wifirecons.utils.Utils
import com.example.wifirecons.utils.Helpers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private const val baseUrl = "https://api.instabridge.com"
    private var url:String = ""


    fun createService(url:String): Retrofit {
        this.url = url
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okhttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun okhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
//            .addInterceptor(interceptor())
            .addInterceptor(headersInterceptor())
            .build()
    }

    private fun interceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun headersInterceptor(): Interceptor {

        val time = Helpers.o()
        val requestId = Helpers.m(url, time)
        val date = Utils.convertTimestampToFormattedString(time * 1000)

        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestWithHeaders = originalRequest.newBuilder()
                .addHeader("Ib-Request-Id", requestId)
                .addHeader("Date", date)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Host", "api.instabridge.com")
                .method(originalRequest.method, originalRequest.body)
                .build()
            chain.proceed(requestWithHeaders)
        }
    }

}