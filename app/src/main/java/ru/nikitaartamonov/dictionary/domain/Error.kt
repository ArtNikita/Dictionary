package ru.nikitaartamonov.dictionary.domain

enum class Error(val code: Int, var msg: String? = "") {
    NOT_A_WORD(1),
    CUSTOM(2)
}