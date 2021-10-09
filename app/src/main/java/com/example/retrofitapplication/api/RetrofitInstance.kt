package com.example.retrofitapplication.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private var retrofit: Retrofit? = null;

    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {

                // create
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

    val repoAPI by lazy {
        retrofitInstance?.create(ApiRequest::class.java)
    }
}