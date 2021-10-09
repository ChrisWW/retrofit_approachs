package com.example.retrofitapplication;

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.example.retrofitapplication.api.RetrofitInstance
import com.example.retrofitapplication.databinding.ActivityMainBinding
import com.example.retrofitapplication.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        backGroundAnimation()
        makeApiRequest()

        binding.floatingActionButton.setOnClickListener {

            binding.floatingActionButton.animate().apply {
                rotationBy(360f)
                duration = 1000
            }.start()

            makeApiRequest()
            binding.ivRandomDog.visibility = View.GONE
        }
    }

    private fun backGroundAnimation() {
        val animationDrawable: AnimationDrawable = binding.rlLayout.background as AnimationDrawable

        animationDrawable.apply() {
            setEnterFadeDuration(1000)
            setExitFadeDuration(3000)
            start()
        }

    }

    private fun makeApiRequest() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val repo = Repository()
                val response = repo.getRandomDog()
                Log.d("Main", "Size: ${response?.fileSizeBytes}")
                if (response?.fileSizeBytes!! < 400000) {
                    withContext(Dispatchers.Main) {
                        Glide.with(applicationContext).load(response.url).into(binding.ivRandomDog)
                        binding.ivRandomDog.visibility = View.VISIBLE
                    }
                } else {
                    withContext(Dispatchers.Main) {
                    val text = "Size too big (AUTO RELOAD)"
                    val duration = Toast.LENGTH_SHORT

                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                    }
                    RetrofitInstance.repoAPI
                    makeApiRequest()
                }

            } catch (e: Exception) {
                Log.e("Main", "Error: ${e.message}")
            }
        }
    }
}
