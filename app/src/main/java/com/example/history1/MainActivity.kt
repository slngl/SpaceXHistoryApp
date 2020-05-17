package com.example.history1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(){
    lateinit var historyList:List<HistoryData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitClient.getClient().create(HistoryService::class.java).getHistory()
            .enqueue(object :retrofit2.Callback<List<HistoryData>>{

                override fun onResponse(
                    call: Call<List<HistoryData>>,
                    response: Response<List<HistoryData>>
                ) {
                    historyList= response.body()!!
                    val myadapter=HistoryAdapter(historyList)
                    recyclerView.adapter=myadapter
                    recyclerView.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)//true verirsek ters sıralama yapacak
                    Toast.makeText(this@MainActivity,"Bağlantı Sağlandı",Toast.LENGTH_LONG).show()

                }

                override fun onFailure(call: Call<List<HistoryData>>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"Bağlantı Sağlanamadı",Toast.LENGTH_LONG).show()
                    Log.e("HATA", call?.request()?.url()?.toString())
                }

            })
    }



}