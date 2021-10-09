package com.example.retrofitapplication.repository

import com.example.retrofitapplication.api.RetrofitInstance
import com.example.retrofitapplication.model.ApiData

class Repository {

    suspend fun getRandomDog(): ApiData? {
        return RetrofitInstance.repoAPI?.getRandomDog()
    }
}