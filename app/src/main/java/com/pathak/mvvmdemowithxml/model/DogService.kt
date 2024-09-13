package com.pathak.mvvmdemowithxml.model

import retrofit2.Call
import retrofit2.http.GET

interface DogService {

    @GET("breeds/image/random")
    fun getRandomDogImage(): Call<DogResponse>
}