package com.example.retrofitapplication.api

import com.example.retrofitapplication.model.ApiData
import retrofit2.http.GET

interface ApiRequest {

    @GET("/woof.json?ref=apilist.fun")
    suspend fun getRandomDog(): ApiData
}