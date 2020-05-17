package com.example.history1

import android.telecom.Call
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HistoryData(val links : Links,
                       val details: String,
                       val fligth_number:Int,
                       val event_date_utc:String,
                       val title:String,
                       var id:Int) {

    class Links {
        @Expose
        @SerializedName("wikipedia")
        var wikipedia: String? = null

        @Expose
        @SerializedName("article")
        var article: String? = null

        @Expose
        @SerializedName("reddit")
        var reddit: String? = null

    }
}