package ru.nikitaartamonov.dictionary.ui.main

interface MainContract {

    interface View {

        fun showError(message: String)
        fun updateList()
    }

    interface Presenter {

        fun attach(view: View)
        fun detach()
        fun addWord(word: String)
    }
}