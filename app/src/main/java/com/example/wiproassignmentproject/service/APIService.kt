package com.example.wiproassignmentproject.service


import com.example.wiproassignmentproject.model.ResponseData
import retrofit2.Call
import retrofit2.http.GET


interface APIService {
    @GET("facts.json")
    fun getList(): Call<ResponseData>
}