package com.example.retrofitapplication.repository

import com.example.retrofitapplication.api.ApiRequest
import com.example.retrofitapplication.api.RetrofitInstance

class Repository {

    suspend fun getRandomDog(): ApiRequest? {
        return RetrofitInstance.repoAPI
    }
}