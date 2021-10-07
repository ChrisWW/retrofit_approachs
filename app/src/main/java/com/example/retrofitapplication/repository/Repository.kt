package com.example.retrofitapplication.repository

import com.example.retrofitapplication.api.RetrofitInstance
import com.example.retrofitapplication.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }
}