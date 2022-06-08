package ru.nikitaartamonov.dictionary.ui.main

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.nikitaartamonov.dictionary.data.di.DiStorage
import ru.nikitaartamonov.dictionary.data.storage.PairOfWords
import ru.nikitaartamonov.dictionary.domain.Error

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
            view?.showError(Error.NOT_A_WORD)
        } else {
            searchWord(word)
        }
    }

    private fun searchWord(word: String) {
        skyEngRepo.getMeaning(word).subscribeBy(
            onError = { error ->
                view?.showError(Error.CUSTOM.also { it.msg = error.message })
            },
            onNext = {
                saveWordToDb(word, it.meaning)
            }
        )
    }

    private fun isActuallyWord(word: String): Boolean = word.isNotEmpty()

    private fun saveWordToDb(word: String, meaning: String) {
        DiStorage.getPairOfWordsDao().get(word).subscribeOn(Schedulers.io()).subscribeBy(
            onComplete = {
                DiStorage.getPairOfWordsDao().add(PairOfWords(word, meaning))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy { view?.updateList() }
            }
        )
    }
}