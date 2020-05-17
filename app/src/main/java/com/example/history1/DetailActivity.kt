package com.example.history1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail2.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail2)

      /*  setSupportActionBar(t_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        t_toolbar.title="Flight Details"*/


        var gelenIntent=intent
        var fN:Int = intent.extras?.get("flight_number").toString().toInt()

            RetrofitClient.getClient().create(HistoryService::class.java).getHistory2(fN)
                .enqueue(object : retrofit2.Callback<List<HistoryData>> {
                    override fun onFailure(call: Call<List<HistoryData>>, t: Throwable) {
                        Toast.makeText(
                            this@DetailActivity,
                            "Bağlantı Sorunu Oluştu " + call?.isExecuted.toString(),
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }


                    override fun onResponse(
                        call: Call<List<HistoryData>>,
                        response: Response<List<HistoryData>>
                    ) {
                        var historyList = response.body()!!
                        Toast.makeText(
                            this@DetailActivity,
                            "ID " + historyList.get(0).id.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        if(gelenIntent != null){
                            tvDTitle.text = historyList.get(0)?.title
                            tvDdetails.text = historyList.get(0)?.details
                            tvDdate.text = historyList.get(0)?.event_date_utc
                            tvDid.text = "ID: ${historyList.get(0).id}"
                        }


                    }

                })


        /*        var gelenIntent = intent
        if(gelenIntent != null){
            imgBuyuk.setImageResource(gelenIntent.getIntExtra("resim",R.drawable.alti))

        }*/
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
