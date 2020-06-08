package com.dnt.demoapp.data.repositories

import com.dnt.demoapp.data.models.Comic
import com.dnt.demoapp.data.models.responses.ApiResponse
import com.dnt.demoapp.data.models.responses.Response

interface IComicsRepository {
    fun getAll(): ApiResponse<Response<Comic>>
}