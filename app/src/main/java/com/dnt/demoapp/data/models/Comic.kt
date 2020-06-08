package com.dnt.demoapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comic(@PrimaryKey val id: Long, val name: String)