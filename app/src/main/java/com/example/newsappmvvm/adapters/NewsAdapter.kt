package com.example.newsappmvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsappmvvm.databinding.ItemArticlePreviewBinding
import com.example.newsappmvvm.models.Article

// Adapter class for displaying news articles in a RecyclerView
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    // ViewHolder class for holding the view of a single article item
    inner class ArticleViewHolder(val binding: ItemArticlePreviewBinding) : RecyclerView.ViewHolder(binding.root)

    // Callback for calculating the diff between two non-null items in a list
    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        // Checks if two items have the same URL (used to determine if items are the same)
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        // Checks if the contents of two items are the same
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    // AsyncListDiffer to handle list updates in the background
    val differ = AsyncListDiffer(this, differCallback)

    // Inflates the layout for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    // Binds data to each item view in the RecyclerView
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            // Loads the article image into the ImageView using Glide
            Glide.with(this).load(article.urlToImage).into(holder.binding.ivArticleImage)
            // Sets the source name, title, description, and publication date of the article
            holder.binding.tvSource.text = article.source?.name
            holder.binding.tvTitle.text = article.title
            holder.binding.tvDescription.text = article.description
            holder.binding.tvPublishedAt.text = article.publishedAt
            // Sets a click listener on the item view
            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    // Returns the size of the current list of articles
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    // Click listener for handling item clicks
    private var onItemClickListener: ((Article) -> Unit)? = null

    // Sets the click listener
    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}