package com.mobilabsolutions.mobilabtaskapp.network

import com.google.gson.GsonBuilder
import com.mobilabsolutions.mobilabtaskapp.BuildConfig
import com.mobilabsolutions.mobilabtaskapp.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


interface RetrofitClientInstance {

    companion object {
        private var gson = GsonBuilder()
                .setLenient()
                .create()

        var retrofit: Retrofit? = null

        fun getRestService(): RestService {
            return getClient()?.create(RestService::class.java)!!
        }

        private fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(makeHttpClient())
                        .build()
            }

            return retrofit
        }

        private fun makeHttpClient() = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(headersInterceptor())
                .build()


        private fun headersInterceptor() = Interceptor { chain ->
            chain.proceed(chain.request().newBuilder()
                    .addHeader("Authorization", "Client-ID " + Constants.CLIENT_ID)
                    .build())
        }
    }
}

