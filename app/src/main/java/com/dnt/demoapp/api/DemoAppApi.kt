package com.dnt.demoapp.api

import com.dnt.demoapp.data.models.Comic
import com.dnt.demoapp.data.models.responses.Response
import retrofit2.Call
import retrofit2.http.GET



interface DemoAppApi {
    @GET("/v1/public/comics")
    fun getComics(): Call<Response<Comic>>
}