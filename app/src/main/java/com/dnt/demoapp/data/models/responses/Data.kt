package com.dnt.demoapp.data.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data<T>(@SerializedName("results") @Expose val result: List<T>)