package com.example.newsappmvvm.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsappmvvm.R
import com.example.newsappmvvm.databinding.ActivityNewsBinding
import com.example.newsappmvvm.databinding.FragmentArticleBinding
import com.example.newsappmvvm.databinding.FragmentBreakingNewsBinding
import com.example.newsappmvvm.ui.NewsActivity
import com.example.newsappmvvm.ui.NewsViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()
    private var binding: FragmentArticleBinding? = null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        binding = FragmentArticleBinding.bind(view)
        val article = args.article
        binding?.webView?.apply {
            webViewClient = WebViewClient()
            article.url?.let { loadUrl(it) }
        }
    }
}