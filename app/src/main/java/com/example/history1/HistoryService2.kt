package com.example.history1

import retrofit2.http.GET
import retrofit2.http.Query

interface HistoryService2 {
    //https://api.spacexdata.com/v3/history?flight_number=6
    @GET("history?flight_number={flight_number}")
    fun getHistory2(@Query("flight_number") flight_number:Int ):retrofit2.Call<HistoryData>
}