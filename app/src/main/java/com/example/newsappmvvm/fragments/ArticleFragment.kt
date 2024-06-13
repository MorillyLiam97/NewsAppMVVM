package com.example.newsappmvvm.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsappmvvm.R
import com.example.newsappmvvm.databinding.FragmentArticleBinding
import com.example.newsappmvvm.ui.NewsActivity
import com.example.newsappmvvm.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar

// Fragment to display an article using a WebView
class ArticleFragment : Fragment(R.layout.fragment_article) {

    // ViewModel to manage UI-related data
    lateinit var viewModel: NewsViewModel

    // Safe args to retrieve arguments passed to the fragment
    val args: ArticleFragmentArgs by navArgs()

    // View binding for the fragment's layout
    private var binding: FragmentArticleBinding? = null

    // Called when the fragment's view has been created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the ViewModel from the activity
        viewModel = (activity as NewsActivity).viewModel

        // Bind the view to the FragmentArticleBinding
        binding = FragmentArticleBinding.bind(view)

        // Get the article from the arguments
        val article = args.article

        // Setup the WebView to load the article's URL
        binding?.webView?.apply {
            webViewClient = WebViewClient() // Ensure links open within the WebView
            article.url?.let { loadUrl(it) } // Load the article's URL
        }

        // Set a click listener on the FloatingActionButton to save the article
        binding?.fab?.setOnClickListener {
            viewModel.saveArticle(article) // Save the article using the ViewModel
            Snackbar.make(view, "Article was successfully saved", Snackbar.LENGTH_SHORT).show() // Show a confirmation message
        }
    }
}