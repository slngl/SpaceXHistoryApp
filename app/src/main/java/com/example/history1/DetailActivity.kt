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

        var gelenIntent=intent
        var fN:Int = intent.extras?.get("flight_number").toString().toInt()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail2)



        RetrofitClient.getClient().create(HistoryService2::class.java).getHistory2(fN).enqueue(object :retrofit2.Callback<HistoryData>{
            override fun onFailure(call: Call<HistoryData>, t: Throwable) {
                Toast.makeText(this@DetailActivity,"HATA",Toast.LENGTH_LONG).show()
                Log.e("HATAA",call.request()?.url()?.toString())
            }

            override fun onResponse(call: Call<HistoryData>, response: Response<HistoryData>) {
                var gelenVeri=response.body()
                tvDTitle.text=gelenVeri?.title
                if (response.body()?.fligth_number !=null){
                    var gelenIntent=intent
                    if(gelenIntent != null){
                        gelenVeri=response.body()
                        tvDTitle.text=gelenVeri?.title
                    }
                }

            }

        })





        /*        var gelenIntent = intent
        if(gelenIntent != null){
            imgBuyuk.setImageResource(gelenIntent.getIntExtra("resim",R.drawable.alti))

        }*/
    }
}
