package com.example.retrofitapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitapplication.model.Post
import com.example.retrofitapplication.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            try {
                val response = repository.getPost()
                myResponse.value = response
            } catch (e : Exception) {
                Log.d("viewmodel", "MainViewModel problem")
            }
        }
    }


}