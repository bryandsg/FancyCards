package com.hashiradev.fancycards

import android.app.Application
import com.hashiradev.fancycards.data.AppDataBase
import com.hashiradev.fancycards.data.FancyCardRepository

class App: Application() {

    val database by lazy { AppDataBase.getDatabase(this) }
    val repository by lazy { FancyCardRepository(database.fancyCardDao()) }
}