package com.example.newsappmvvm.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappmvvm.R
import com.example.newsappmvvm.adapters.NewsAdapter
import com.example.newsappmvvm.databinding.FragmentBreakingNewsBinding
import com.example.newsappmvvm.databinding.FragmentSavedNewsBinding
import com.example.newsappmvvm.ui.NewsActivity
import com.example.newsappmvvm.ui.NewsViewModel

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {
    private var binding : FragmentSavedNewsBinding? = null
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        binding = FragmentSavedNewsBinding.bind(view)
        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articleFragment,
                bundle
            )
        }
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding?.rvSavedNews?.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)

        }

    }
}