package com.uc.moviecatalog.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.uc.moviecatalog.adapter.companyadapter
import com.uc.moviecatalog.adapter.genreadapter
import com.uc.moviecatalog.adapter.languageadapter
import com.uc.moviecatalog.viewmodel.NowPlayingViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.uc.moviecatalog.databinding.ActivityMovieDetailBinding
import com.uc.moviecatalog.helper.const


@AndroidEntryPoint
class MovieDetail : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var nowPlayingViewModel: NowPlayingViewModel
    private lateinit var genreAdapter: genreadapter
    private lateinit var languageAdapter: languageadapter
    private lateinit var companyAdapter: companyadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie_id = intent.getIntExtra("movie_id", 0)
        Toast.makeText(applicationContext, "Movie ID: ${movie_id}", Toast.LENGTH_SHORT)

        nowPlayingViewModel = ViewModelProvider(this)[NowPlayingViewModel::class.java]
        nowPlayingViewModel.getMovieDetail(const.API_key, movie_id)

        nowPlayingViewModel.movieDetail.observe(this) { response ->
            binding.progressBar2.visibility = View.VISIBLE

            binding.tvMovieTitle.apply {
                text = response.title
            }
            binding.ivMovie.apply {
                Picasso.get().load(const.Img_url + response.backdrop_path).into(binding.ivMovie)
              }

            binding.tvDetail.apply {
                text = response.overview
            }
            binding.rvGenre.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false)
            genreAdapter = genreadapter(response.genres)
            binding.rvGenre.adapter = genreAdapter

            binding.rvLangauge.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false)
            languageAdapter = languageadapter(response.spoken_languages)
            binding.rvLangauge.adapter = languageAdapter

            binding.rvProdcompany.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false)
            companyAdapter = companyadapter(response.production_companies)
            binding.rvProdcompany.adapter = companyAdapter

            binding.progressBar2.visibility = View.INVISIBLE
        }




    }
}