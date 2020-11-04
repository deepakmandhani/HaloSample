package com.example.haloapp.ui.main

import NewsResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haloapp.ui.main.service.ApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private var mediatorLiveData: MediatorLiveData<NewsResponse> = MediatorLiveData()
    private var failureLiveData: MediatorLiveData<Boolean> = MediatorLiveData()

    fun getNewsLiveData(): LiveData<NewsResponse> = mediatorLiveData
    fun getFailureLiveData(): LiveData<Boolean> = failureLiveData

    fun fetchNews(query: String, page: Int) {
        viewModelScope.launch {
            ApiService.getRetrofit().fetchNews(query, page)
                .enqueue(object : Callback<NewsResponse> {
                    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                        failureLiveData.postValue(true)
                    }

                    override fun onResponse(
                        call: Call<NewsResponse>,
                        response: Response<NewsResponse>
                    ) {
                        if (response.isSuccessful && response.code() == 200) {
                            val newsResponse = response.body()
                            mediatorLiveData.postValue(newsResponse)
                            return
                        }
                        failureLiveData.postValue(false)
                    }
                })
        }
    }

}
