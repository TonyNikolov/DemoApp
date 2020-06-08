package com.dnt.demoapp.data.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("message")
    @Expose
    val message: String?,
    @SerializedName("data")
    @Expose
    val data: Data<T>
)