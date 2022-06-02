package ru.nikitaartamonov.dictionary

import android.app.Application
import androidx.room.Room
import ru.nikitaartamonov.dictionary.data.di.DiStorage
import ru.nikitaartamonov.dictionary.data.storage.PairOfWordsDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DiStorage.initDataBase(applicationContext)
    }
}