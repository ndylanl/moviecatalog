package com.uc.moviecatalog.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uc.moviecatalog.model.Genre
import com.uc.moviecatalog.model.MovieDetails
import com.uc.moviecatalog.repository.NowPlayingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.uc.moviecatalog.model.Result
import kotlinx.coroutines.launch

@HiltViewModel
class NowPlayingViewModel @Inject constructor(private val repository: NowPlayingRepository) :
    ViewModel() {


    //get now playing data
    val _nowPlaying: MutableLiveData<ArrayList<Result>> by lazy {
        MutableLiveData<ArrayList<Result>>()
    }
    val nowPlaying: LiveData<ArrayList<Result>> get() = _nowPlaying

    fun getNowPlaying(apiKey: String, language: String, page: Int) = viewModelScope.launch {
        repository.getNowPlayingData(apiKey, language, page).let { response ->
            if (response.isSuccessful) {
                _nowPlaying.postValue(response.body()?.results as ArrayList<Result>)
            } else {
                Log.e("Get Data", "Failed!")
            }
        }
    }


    //get movie detail
    val _movieDetails: MutableLiveData<MovieDetails> by lazy {
        MutableLiveData<MovieDetails>()
    }
    val movieDetail: LiveData<MovieDetails> get() = _movieDetails

    fun getMovieDetail(apiKey: String, MovieId: Int) = viewModelScope.launch {
        repository.getMovieDetails(apiKey, MovieId).let { response ->
            if (response.isSuccessful) {
                _movieDetails.postValue(response.body() as MovieDetails)
            } else {
                Log.e("Get Data", "Failed!")
            }
        }
    }

}