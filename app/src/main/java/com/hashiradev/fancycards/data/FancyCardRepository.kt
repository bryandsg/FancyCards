package com.hashiradev.fancycards.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FancyCardRepository(private val dao: FancyCardDao) {

    fun insert(fancyCard: FancyCard) = runBlocking {
        launch(Dispatchers.IO) {
            dao.insert(fancyCard)
        }
    }

    fun getAll() = dao.getAll()

}