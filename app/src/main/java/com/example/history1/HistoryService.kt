package com.example.history1

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HistoryService {
    @GET("history")
    fun getHistory():retrofit2.Call<List<HistoryData>>

}
