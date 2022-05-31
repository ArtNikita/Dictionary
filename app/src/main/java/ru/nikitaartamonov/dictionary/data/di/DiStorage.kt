package ru.nikitaartamonov.dictionary.data.di

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.nikitaartamonov.dictionary.data.network.SkyEngApi
import ru.nikitaartamonov.dictionary.ui.main.MainActivityPresenter
import ru.nikitaartamonov.dictionary.ui.main.MainContract

object DiStorage {

    val skyEndApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dictionary.skyeng.ru/api/public/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        retrofit.create(SkyEngApi::class.java)
    }

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