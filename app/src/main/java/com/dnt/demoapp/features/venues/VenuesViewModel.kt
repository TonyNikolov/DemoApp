package com.dnt.demoapp.features.venues

import androidx.lifecycle.ViewModel
import com.dnt.demoapp.data.repositories.IComicsRepository
import javax.inject.Inject

class VenuesViewModel @Inject constructor(private val venuesRepository: IComicsRepository) :
    ViewModel() {

    fun getVenues() {
        Thread(Runnable {
            val venues = venuesRepository.getAll()
            val b = 5
        }).start()
    }
}