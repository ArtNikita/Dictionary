package ru.nikitaartamonov.dictionary.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface PairOfWordsDao {

    @Query("SELECT * FROM PairOfWords")
    fun getAll(): Maybe<List<PairOfWords>>

    @Query("SELECT * FROM PairOfWords WHERE word = :word")
    fun get(word: String): Maybe<PairOfWords>

    @Insert
    fun add(pairOfWords: PairOfWords): Completable
}