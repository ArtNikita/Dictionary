package ru.nikitaartamonov.dictionary.ui.main

class MainActivityPresenter : MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun attach(view: MainContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }
}