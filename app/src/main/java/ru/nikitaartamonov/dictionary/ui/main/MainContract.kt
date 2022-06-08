package ru.nikitaartamonov.dictionary.ui.main

import ru.nikitaartamonov.dictionary.domain.Error

interface MainContract {

    interface View {

        fun showError(error: Error)
        fun updateList()
    }

    interface Presenter {

        fun attach(view: View)
        fun detach()
        fun addWord(word: String)
    }
}