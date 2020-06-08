package com.dnt.demoapp.data.repositories

import com.dnt.demoapp.api.DemoAppApi
import com.dnt.demoapp.data.models.Comic
import com.dnt.demoapp.data.models.responses.ApiResponse
import com.dnt.demoapp.data.models.responses.Response
import com.dnt.demoapp.data.persistence.database.dao.ComicsDao

class ComicsRepository(
    val demoAppApi: DemoAppApi,
    val comicsDao: ComicsDao
) : IComicsRepository {
    override fun getAll(): ApiResponse<Response<Comic>> {
        return ApiResponse(demoAppApi.getComics())
    }

}