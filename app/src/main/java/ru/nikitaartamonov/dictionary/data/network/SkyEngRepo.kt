package ru.nikitaartamonov.dictionary.data.network

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.nikitaartamonov.dictionary.domain.WordMeaning
import javax.inject.Inject

class SkyEngRepo @Inject constructor(private val skyEngApi: SkyEngApi) {

    fun getMeaning(word: String): Observable<WordMeaning> {
        val wordMeaning = PublishSubject.create<WordMeaning>()
        skyEngApi.search(word)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    wordMeaning.onError(it)
                    wordMeaning.onComplete()
                },
                onSuccess = { meaningsList ->
                    if (meaningsList.isEmpty()) {
                        wordMeaning.onError(Throwable("Nothing was found"))
                        wordMeaning.onComplete()
                    } else {
                        val meaning = meaningsList[0].meanings[0].translation.text
                        wordMeaning.onNext(WordMeaning(meaning))
                        wordMeaning.onComplete()
                    }
                }
            )
        return wordMeaning
    }
}