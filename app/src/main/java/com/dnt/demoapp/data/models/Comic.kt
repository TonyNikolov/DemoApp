package com.dnt.demoapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Comic(
    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("title")
    @Expose
    val title: String
)