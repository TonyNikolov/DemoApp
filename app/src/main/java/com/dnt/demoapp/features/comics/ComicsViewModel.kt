package com.dnt.demoapp.features.comics

import androidx.lifecycle.*
import com.dnt.demoapp.data.models.Comic
import com.dnt.demoapp.data.models.responses.ApiResponse
import com.dnt.demoapp.data.models.responses.Response
import com.dnt.demoapp.data.models.state.Resource
import com.dnt.demoapp.data.repositories.IComicsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ComicsViewModel @Inject constructor(private val comicsRepository: IComicsRepository) :
    ViewModel() {
    private val _comicsLiveData = MutableLiveData<Resource<Response<Comic>>>()
    val comicsLiveData: LiveData<Resource<Response<Comic>>> = _comicsLiveData

    fun getComics() {
        viewModelScope.launch(Dispatchers.IO) {
            _comicsLiveData.postValue(Resource.loading())
            val comicsResponse: ApiResponse<Response<Comic>> = comicsRepository.getAll()
            _comicsLiveData.postValue(Resource(comicsResponse))
        }
    }
}