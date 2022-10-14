package com.uc.moviecatalog.repository

import com.uc.moviecatalog.retrofit.EndPointAPI
import javax.inject.Inject

class NowPlayingRepository @Inject constructor(private val api: EndPointAPI) {
    suspend fun getNowPlayingData(apiKey: String, language: String, page: Int) = api.getNowPlaying(apiKey, language, page)

    suspend fun getMovieDetails(apiKey: String, MovieId: Int) =
        api.getMovieDetails(MovieId, apiKey)
}