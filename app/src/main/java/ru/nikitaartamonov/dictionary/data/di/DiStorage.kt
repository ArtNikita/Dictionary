package ru.nikitaartamonov.dictionary.data.di

import android.content.Context
import androidx.room.Room
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.nikitaartamonov.dictionary.DATA_BASE_NAME
import ru.nikitaartamonov.dictionary.data.network.SkyEngApi
import ru.nikitaartamonov.dictionary.data.network.SkyEngRepo
import ru.nikitaartamonov.dictionary.data.storage.PairOfWordsDao
import ru.nikitaartamonov.dictionary.data.storage.PairOfWordsDataBase
import ru.nikitaartamonov.dictionary.ui.main.MainActivityPresenter
import ru.nikitaartamonov.dictionary.ui.main.MainContract

object DiStorage {

    fun getSkyEngRepo(): SkyEngRepo = SkyEngRepo(skyEngApi)

    fun getMainActivityPresenter(): MainContract.Presenter {
        return mainActivityPresenter ?: MainActivityPresenter().apply {
            mainActivityPresenter = this
        }
    }

    fun clearMainActivityPresenter() {
        mainActivityPresenter = null
    }

    fun getPairOfWordsDao(): PairOfWordsDao = dataBase.pairOfWordsDao

    fun initDataBase(context: Context) {
        dataBase = Room.databaseBuilder(
            context, PairOfWordsDataBase::class.java, DATA_BASE_NAME
        ).build()
    }

    private lateinit var dataBase: PairOfWordsDataBase

    private val skyEngApi: SkyEngApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dictionary.skyeng.ru/api/public/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        retrofit.create(SkyEngApi::class.java)
    }

    private var mainActivityPresenter: MainContract.Presenter? = null
}