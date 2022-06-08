package ru.nikitaartamonov.dictionary.domain

data class WordMeaningsNetworkResponse(
    val id: Int,
    val meanings: List<Meaning>,
    val text: String
)