package com.example.haloapp.ui.main

import Hits
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.haloapp.R
import kotlinx.android.synthetic.main.news_rv_item.view.*

/**
 * Created by Deepak Mandhani on 2020-06-26.
 */

class NewsRVAdapter(private var newsList: ArrayList<Hits>, private val callback: (Hits) -> Unit) :
    RecyclerView.Adapter<NewsRVAdapter.NewsVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        return NewsVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_rv_item,
                parent,
                false
            ), callback
        )
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsVH, position: Int) {
        holder.onBind(newsList[position])
    }

    fun updateNews(news: List<Hits>) {
        newsList.clear()
        newsList.addAll(news)
        notifyDataSetChanged()
    }

    fun addNews(news: List<Hits>) {
        newsList.addAll(news)
        notifyDataSetChanged()
    }

    class NewsVH(itemView: View, callback: (Hits) -> Unit) : RecyclerView.ViewHolder(itemView) {

        fun onBind(hits: Hits) {
            itemView.tv_title.text = hits.title
            itemView.iv_news.text = hits.author
//            Picasso.get().load(hits.story_url).placeholder(R.mipmap.ic_launcher).into(itemView.iv_news)
            itemView.tag = hits
            itemView.setOnClickListener(onClickListener)
        }

        private val onClickListener = View.OnClickListener {
            val hit = it.tag as Hits
            callback(hit)
        }

    }
}