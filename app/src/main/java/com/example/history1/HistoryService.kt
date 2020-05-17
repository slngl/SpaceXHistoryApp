package com.example.history1

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HistoryService {
    //https://api.spacexdata.com/v3/history
    @GET("history")
    fun getHistory():retrofit2.Call<List<HistoryData>>
    //https://api.spacexdata.com/v3/history?flight_number=6
    @GET("history?")
    fun getHistory2(  @Query("flight_number") flight_number:Int ):retrofit2.Call<List<HistoryData>>


}
