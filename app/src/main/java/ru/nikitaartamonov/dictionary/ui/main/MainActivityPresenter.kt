package ru.nikitaartamonov.dictionary.ui.main

class MainActivityPresenter : MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun attach(view: MainContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun addWord(word: String) {
        if (!isActuallyWord(word)){
            view?.showError("You should enter word!") //yeah, i know that i shouldn't do this way
        } else {
            //todo search word
        }
    }

    private fun isActuallyWord(word: String): Boolean {
        return word.isNotEmpty() && !word.contains(' ') && word.all { it.isLetter() }
    }
}