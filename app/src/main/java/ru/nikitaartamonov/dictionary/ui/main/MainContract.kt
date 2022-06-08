package ru.nikitaartamonov.dictionary.ui.main

import androidx.lifecycle.LiveData
import ru.nikitaartamonov.dictionary.domain.Error
import ru.nikitaartamonov.dictionary.domain.Event

interface MainContract {

    interface ViewModel {

        fun addWord(word: String)
        val showErrorLiveData: LiveData<Event<Error>>
        val updateListLiveData: LiveData<Event<Unit>>
    }
}