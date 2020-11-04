package com.example.haloapp.ui.main

import Hits
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.haloapp.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var newsAdapter: NewsRVAdapter? = null
    private var searchFor = ""
    private var nextPage: Int = 0
    private var isLoading = false
    private var isLastPage = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory
            .getInstance(requireActivity().application).create(MainViewModel::class.java)
        initView()
        initObserver()
    }

    private fun initView() {
//        et_search.addTextChangedListener(watcher)
        btn_news.setOnClickListener { onSearchClick() }
        newsAdapter = NewsRVAdapter(arrayListOf()) {
            val intent = Intent(requireActivity(), NewsDetailActivity::class.java)
            intent.putExtra(NewsDetailActivity.NEWS_URL, it.url)
            startActivity(intent)
        }

        val layoutManager = LinearLayoutManager(requireContext())
        rv_news.layoutManager = layoutManager
        rv_news.adapter = newsAdapter

        rv_news.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= 20
                    ) {
                        isLoading = true
                        fetchNews(searchFor, nextPage)
                    }
                }
            }
        })
    }


    private fun initObserver() {
        viewModel.getNewsLiveData().observe(viewLifecycleOwner, Observer {
            pb_news.visibility = View.GONE
            it?.let {
                if (it.hits.isNotEmpty())
                    updateAdapter(it.hits)
                isLoading = false
                if (it.nbPages == 0)
                    isLastPage = true
                nextPage = it.page + 1
            }
        })

        viewModel.getFailureLiveData().observe(viewLifecycleOwner, Observer {
            pb_news.visibility = View.GONE
//            if (it)

        })

    }

    private fun fetchNews(searchFor: String, nextPage: Int) {
        pb_news.visibility = View.VISIBLE
        viewModel.fetchNews(searchFor, nextPage)
    }

    private fun updateAdapter(hits: List<Hits>) {
        if (nextPage == 0)
            newsAdapter?.updateNews(hits)
        else
            newsAdapter?.addNews(hits)

    }

    private fun onSearchClick() {
        nextPage = 0
        val query = et_search.text.toString()
        fetchNews(query, nextPage)
    }


    private val watcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
    }
}
