package com.dnt.demoapp.features.comics

import androidx.lifecycle.ViewModel
import com.dnt.demoapp.data.repositories.IComicsRepository
import javax.inject.Inject

class ComicsViewModel @Inject constructor(private val comicsRepository: IComicsRepository) :
    ViewModel() {

    fun getComics() {
        Thread(Runnable {
            val venues = comicsRepository.getAll()
            val b = 5
        }).start()
    }
}