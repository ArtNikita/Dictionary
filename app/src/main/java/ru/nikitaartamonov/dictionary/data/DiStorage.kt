package ru.nikitaartamonov.dictionary.data

import ru.nikitaartamonov.dictionary.ui.main.MainActivityPresenter
import ru.nikitaartamonov.dictionary.ui.main.MainContract

object DiStorage {

    fun getMainActivityPresenter(): MainContract.Presenter {
        return mainActivityPresenter ?: MainActivityPresenter().apply {
            mainActivityPresenter = this
        }
    }

    fun clearMainActivityPresenter() {
        mainActivityPresenter = null
    }

    private var mainActivityPresenter: MainContract.Presenter? = null
}