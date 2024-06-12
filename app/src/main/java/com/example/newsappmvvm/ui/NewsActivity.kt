package com.example.newsappmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsappmvvm.R
import com.example.newsappmvvm.databinding.ActivityNewsBinding
import com.example.newsappmvvm.db.ArticleDb
import com.example.newsappmvvm.repository.NewsRepository
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsRepository = NewsRepository(ArticleDb(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment)
        val navController = navHostFragment?.findNavController()

        if (navController != null) {
            binding.bottomNavigationView.setupWithNavController(navController)
        }

    }
}
