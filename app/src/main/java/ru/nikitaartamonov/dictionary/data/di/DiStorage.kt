package ru.nikitaartamonov.dictionary.data.di

import android.content.Context
import androidx.room.Room
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.nikitaartamonov.dictionary.BASE_URL
import ru.nikitaartamonov.dictionary.DATA_BASE_NAME
import ru.nikitaartamonov.dictionary.data.network.SkyEngApi
import ru.nikitaartamonov.dictionary.data.network.SkyEngRepo
import ru.nikitaartamonov.dictionary.data.storage.PairOfWordsDao
import ru.nikitaartamonov.dictionary.data.storage.PairOfWordsDataBase

object DiStorage {

    fun getSkyEngRepo(): SkyEngRepo = SkyEngRepo(skyEngApi)

    fun getPairOfWordsDao(): PairOfWordsDao = dataBase.pairOfWordsDao

    fun initDataBase(context: Context) {
        dataBase = Room.databaseBuilder(
            context, PairOfWordsDataBase::class.java, DATA_BASE_NAME
        ).build()
    }

    private lateinit var dataBase: PairOfWordsDataBase

    private val skyEngApi: SkyEngApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        retrofit.create(SkyEngApi::class.java)
    }
}