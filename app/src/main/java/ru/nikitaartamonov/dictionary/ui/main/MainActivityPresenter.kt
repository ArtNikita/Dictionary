package ru.nikitaartamonov.dictionary.ui.main

import android.util.Log
import io.reactivex.rxjava3.kotlin.subscribeBy
import ru.nikitaartamonov.dictionary.data.di.DiStorage

class MainActivityPresenter : MainContract.Presenter {

    private var view: MainContract.View? = null
    private val skyEngRepo = DiStorage.getSkyEngRepo()

    override fun attach(view: MainContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun addWord(word: String) {
        if (!isActuallyWord(word)) {
            view?.showError("You should enter word!") //yeah, i know that i shouldn't do this way
        } else {
            searchWord(word)
        }
    }

    private fun searchWord(word: String) {
        skyEngRepo.getMeaning(word).subscribeBy(
            onError = {
                view?.showError(it.message ?: "Something wrong, try later")
            },
            onNext = {
                Log.i("@@@", it.meaning)
            }
        )
    }

    private fun isActuallyWord(word: String): Boolean {
        return word.isNotEmpty() && !word.contains(' ') && word.all { it.isLetter() }
    }
}