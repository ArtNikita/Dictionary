package ru.nikitaartamonov.dictionary.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.nikitaartamonov.dictionary.BASE_URL
import ru.nikitaartamonov.dictionary.DATA_BASE_NAME
import ru.nikitaartamonov.dictionary.data.network.SkyEngApi
import ru.nikitaartamonov.dictionary.data.storage.PairOfWordsDao
import ru.nikitaartamonov.dictionary.data.storage.PairOfWordsDataBase
import javax.inject.Singleton

@Module
class MainModule {

    @Singleton
    @Provides
    fun provideSkyEngApi(): SkyEngApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        return retrofit.create(SkyEngApi::class.java)
    }

    @Singleton
    @Provides
    fun providePairOfWordsDao(context: Context): PairOfWordsDao {
        return Room.databaseBuilder(
            context, PairOfWordsDataBase::class.java, DATA_BASE_NAME
        ).build().pairOfWordsDao
    }
}