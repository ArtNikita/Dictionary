package ru.nikitaartamonov.dictionary.data.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nikitaartamonov.dictionary.domain.WordMeaningsNetworkResponse

interface SkyEngApi {

    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Single<List<WordMeaningsNetworkResponse>>
}