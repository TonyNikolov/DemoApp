package com.dnt.demoapp.features.comics

import androidx.lifecycle.*
import com.dnt.demoapp.data.models.Comic
import com.dnt.demoapp.data.models.responses.ApiResponse
import com.dnt.demoapp.data.models.responses.Response
import com.dnt.demoapp.data.models.state.Resource
import com.dnt.demoapp.data.repositories.IComicsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class ComicsViewModel @Inject constructor(private val comicsRepository: IComicsRepository, private val coroutineScope: CoroutineScope) :
    ViewModel() {
    private val _comicsLiveData = MutableLiveData<Resource<Response<Comic>>>()
    val comicsLiveData: LiveData<Resource<Response<Comic>>> = _comicsLiveData

    fun getComics() {
        coroutineScope.launch {
            _comicsLiveData.postValue(Resource.loading())
            val comicsResponse: ApiResponse<Response<Comic>> = comicsRepository.getAll()
            _comicsLiveData.postValue(Resource(comicsResponse))
        }
    }
}