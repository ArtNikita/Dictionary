package ru.nikitaartamonov.dictionary.data.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PairOfWords(
    @PrimaryKey
    val word: String,
    val meaning: String
)
