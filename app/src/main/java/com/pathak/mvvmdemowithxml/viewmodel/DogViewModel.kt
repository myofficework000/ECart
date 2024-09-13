package com.pathak.mvvmdemowithxml.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pathak.mvvmdemowithxml.model.DogResponse
import com.pathak.mvvmdemowithxml.model.DogService
import com.pathak.mvvmdemowithxml.model.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DogViewModel : ViewModel() {

    private val dogService = RetrofitBuilder.getRetrofit().create(DogService::class.java)
    private val _dogResponse = MutableLiveData<DogResponse>()
    val dogResponse: LiveData<DogResponse> = _dogResponse
    private var _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchRandomDog() {
        dogService.getRandomDogImage().enqueue(object : Callback<DogResponse> {
            override fun onResponse(p0: Call<DogResponse>, p1: Response<DogResponse>) {
                if (p1.isSuccessful) {
                    _dogResponse.value = p1.body()
                }
            }

            override fun onFailure(p0: Call<DogResponse>, p1: Throwable) {
                p1.printStackTrace()
                _error.value = p1.message
            }
        })
    }
}