package com.bcare.bcareapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bcare.bcareapp.R
import com.bcare.bcareapp.data.remote.response.artikel.DataItem
import com.bcare.bcareapp.databinding.ItemArticleBinding
import com.bumptech.glide.Glide

class ListArtikelAdapter(private val listArticle: List<DataItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ArticleViewHolder -> holder.bind(listArticle[position] as DataItem)
        }
    }

    override fun getItemCount(): Int = listArticle.size


    inner class ArticleViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: DataItem) {
            val subtitle = article.subTitle
            val imageUrl = article.image
            val desc = article.content
            binding.tvJudulArtikel.text = subtitle
            binding.tvIsiArtikel.text = desc
            Glide.with(binding.root)
                .load(imageUrl)
                .placeholder(R.drawable.lovemom)
                .into(binding.imgArtikel)


            binding.root.setOnClickListener {
                onItemClickCallback.onItemArticleClicked(article)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemArticleClicked(item: DataItem)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}