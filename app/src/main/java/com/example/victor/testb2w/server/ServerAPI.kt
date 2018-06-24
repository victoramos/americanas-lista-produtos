package com.keepbrain.app.server


import com.example.victor.americanas.server.requests.ProductRequests
import com.keepbrain.app.core.server.ServerConstants
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException


class ServerAPI private constructor(private val retrofit: Retrofit){
    fun containers(): ProductRequests {
        return retrofit.create(ProductRequests::class.java)
    }

    companion object{
        val instance: ServerAPI by lazy {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url()

                val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apiClientKey", ServerConstants.API_CLIENT_KEY)
                        .addQueryParameter("apiKey", ServerConstants.API_KEY)
                        .build()

                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
                        .url(url)

                val request = requestBuilder.build()
                chain.proceed(request)
            }

            val retrofit = Retrofit.Builder()
                    .baseUrl(ServerConstants.DEFAULT_DOMAIN)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient.build())
                    .build()
            ServerAPI(retrofit)
        }
    }
}