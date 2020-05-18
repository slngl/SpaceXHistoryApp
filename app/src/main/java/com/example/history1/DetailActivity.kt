package com.example.history1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import kotlinx.android.synthetic.main.activity_detail2.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail2)

        imgBack.setOnClickListener { v-> onBackPressed()  }


        var gelenIntent=intent
        var fN:Int = intent.extras?.get("flight_number").toString().toInt()     //intent ile flight_number getirip onunla ikinci GET isteğini yolladım

            RetrofitClient.getClient().create(HistoryService::class.java).getHistory2(fN)
                .enqueue(object : retrofit2.Callback<List<HistoryData>> {
                    override fun onFailure(call: Call<List<HistoryData>>, t: Throwable) {
                        Toast.makeText(
                            this@DetailActivity,
                            "Bağlantı Sorunu Oluştu " ,
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }


                    override fun onResponse(
                        call: Call<List<HistoryData>>,
                        response: Response<List<HistoryData>>
                    ) {
                        var historyList = response.body()!!
                        if(gelenIntent != null){
                            tvDTitle.text = historyList.get(0)?.title
                            tvDdetails.text = historyList.get(0)?.details
                            tvDdate.text = historyList.get(0)?.event_date_utc
                            tvDid.text = "ID: ${historyList.get(0).id}"

                            //Link=null ise bir şey yapılmasın, null değilse de gelen link açılsın
                            if(historyList[0].links.reddit!=null) {
                                tvReddit.setOnClickListener { v ->
                                    var uri = Uri.parse(historyList.get(0).links.reddit)
                                    var uriintent = Intent(Intent.ACTION_VIEW, uri)
                                    startActivity(uriintent)
                                }
                            }
                            if(historyList[0].links.wikipedia!=null) {
                                tvWiki.setOnClickListener { v ->
                                    var uri = Uri.parse(historyList.get(0).links.wikipedia)
                                    var uriintent = Intent(Intent.ACTION_VIEW, uri)
                                    startActivity(uriintent)
                                }
                            }
                            if(historyList[0].links.article!=null) {
                                tvArticle.setOnClickListener { v ->
                                    var uri = Uri.parse(historyList.get(0).links.article)
                                    var uriintent = Intent(Intent.ACTION_VIEW, uri)
                                    startActivity(uriintent)
                                }
                            }
                        }//if(gelenintent!=null)
                    }
                })


    }

}
