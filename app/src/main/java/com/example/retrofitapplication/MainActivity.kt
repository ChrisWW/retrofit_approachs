package com.example.retrofitapplication;

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitapplication.repository.Repository
import com.example.retrofitapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful) {
            Log.d("Response", response.body()?.userId.toString())
            Log.d("Response", response.body()?.id.toString())
            Log.d("Response", response.body()?.title.toString())
            binding.tx1.text = response.body()?.title!!
            Log.d("Response", response.body()?.body.toString())

        } else {
                Log.d("Response", response.errorBody().toString())
                binding.tx1.text = response.code().toString()
            }})
    }

}