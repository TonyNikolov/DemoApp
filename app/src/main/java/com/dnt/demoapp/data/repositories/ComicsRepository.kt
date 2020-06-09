package com.dnt.demoapp.data.repositories

import com.dnt.demoapp.api.DemoAppApi
import com.dnt.demoapp.data.models.Comic
import com.dnt.demoapp.data.models.responses.ApiResponse
import com.dnt.demoapp.data.models.responses.Response
import com.dnt.demoapp.data.persistence.database.dao.ComicsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ComicsRepository(
    val demoAppApi: DemoAppApi,
    val comicsDao: ComicsDao
) : IComicsRepository {
    override suspend fun getAll(): ApiResponse<Response<Comic>> {
        return ApiResponse(demoAppApi.getComics())
    }

}