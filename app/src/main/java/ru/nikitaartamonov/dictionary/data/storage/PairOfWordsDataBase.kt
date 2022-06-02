package ru.nikitaartamonov.dictionary.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PairOfWords::class], version = 1)
abstract class PairOfWordsDataBase : RoomDatabase() {

    abstract val pairOfWordsDao: PairOfWordsDao
}