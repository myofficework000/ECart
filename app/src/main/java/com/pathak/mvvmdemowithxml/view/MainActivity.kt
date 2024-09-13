package com.pathak.mvvmdemowithxml.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pathak.mvvmdemowithxml.databinding.ActivityMainBinding
import com.pathak.mvvmdemowithxml.viewmodel.DogViewModel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        viewModel = ViewModelProvider(this)[DogViewModel::class.java]

        with(binding) {
            btnFetchRandomDog.setOnClickListener {
                viewModel.fetchRandomDog()
            }

            //Observer for dog image
            viewModel.dogResponse.observe(this@MainActivity) { response ->
                Picasso.get().load(response.message).into(dogimage)
                imageUrl.text = response.message
            }

            //Observer for error
            viewModel.error.observe(this@MainActivity) { error ->
                imageUrl.text = error
            }
        }
    }
}