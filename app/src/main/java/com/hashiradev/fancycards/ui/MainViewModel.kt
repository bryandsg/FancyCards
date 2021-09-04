package com.hashiradev.fancycards.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hashiradev.fancycards.data.FancyCard
import com.hashiradev.fancycards.data.FancyCardRepository
import java.lang.IllegalArgumentException

class MainViewModel(private val fancyCardRepository: FancyCardRepository) : ViewModel() {

    fun insert(fancyCard: FancyCard) {
        fancyCardRepository.insert(fancyCard)
    }

    fun getAll(): LiveData<List<FancyCard>> {
        return fancyCardRepository.getAll()
    }
}

class MainViewModelFactory(private val repository: FancyCardRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if ( modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return  MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }



}