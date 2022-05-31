package ru.nikitaartamonov.dictionary.ui.main

interface MainContract {

    interface View {

        fun showError(message: String)
    }

    interface Presenter {

        fun attach(view: View)
        fun detach()
        fun addWord(word: String)
    }
}