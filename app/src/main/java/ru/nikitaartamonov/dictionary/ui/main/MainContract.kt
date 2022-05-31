package ru.nikitaartamonov.dictionary.ui.main

interface MainContract {

    interface View {

    }

    interface Presenter {

        fun attach(view: View)
        fun detach()
    }
}