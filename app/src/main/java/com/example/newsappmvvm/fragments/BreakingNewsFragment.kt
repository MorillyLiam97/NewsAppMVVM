package com.example.newsappmvvm.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappmvvm.R
import com.example.newsappmvvm.adapters.NewsAdapter
import com.example.newsappmvvm.databinding.FragmentBreakingNewsBinding
import com.example.newsappmvvm.ui.NewsActivity
import com.example.newsappmvvm.ui.NewsViewModel
import com.example.newsappmvvm.util.Resource

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    private var binding : FragmentBreakingNewsBinding? = null
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    val TAG = "BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        binding = FragmentBreakingNewsBinding.bind(view)
        setupRecyclerView()

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })
    }

    private fun hideProgressBar() {
        binding?.paginationProgressBar?.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding?.paginationProgressBar?.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding?.rvBreakingNews?.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)

        }

    }
}