package com.uc.moviecatalog.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.uc.moviecatalog.adapter.nowplayingadapter
import com.uc.moviecatalog.databinding.ActivityMainBinding
import com.uc.moviecatalog.helper.const
import com.uc.moviecatalog.viewmodel.NowPlayingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: nowplayingadapter
    private lateinit var viewModel: NowPlayingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(NowPlayingViewModel::class.java)
        viewModel.getNowPlaying(const.API_key, "en-us", 1)

        viewModel.nowPlaying.observe(this, Observer { response->
            binding.RVMain.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = nowplayingadapter(response)
            binding.RVMain.adapter = adapter

        })

    }
}