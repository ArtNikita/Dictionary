package ru.nikitaartamonov.dictionary.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.nikitaartamonov.dictionary.data.di.DiStorage
import ru.nikitaartamonov.dictionary.data.storage.PairOfWords
import ru.nikitaartamonov.dictionary.domain.Error
import ru.nikitaartamonov.dictionary.domain.Event

class MainActivityViewModel : ViewModel(), MainContract.ViewModel {

    private val skyEngRepo = DiStorage.getSkyEngRepo()

    override val showErrorLiveData: LiveData<Event<Error>> = MutableLiveData()
    override val updateListLiveData: LiveData<Event<Unit>> = MutableLiveData()

    override fun addWord(word: String) {
        if (!isActuallyWord(word)) {
            showErrorLiveData.postValue(Event(Error.NOT_A_WORD))
        } else {
            searchWord(word)
        }
    }

    private fun searchWord(word: String) {
        skyEngRepo.getMeaning(word).subscribeBy(
            onError = { error ->
                showErrorLiveData.postValue(Event(Error.CUSTOM.also { it.msg = error.message }))
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
                    .subscribeBy { updateListLiveData.postValue(Event(Unit)) }
            }
        )
    }

    private fun <T> LiveData<T>.postValue(value: T) {
        (this as MutableLiveData).postValue(value)
    }
}