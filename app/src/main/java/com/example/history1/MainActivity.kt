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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager=LinearLayoutManager(this)
        RetrofitClient.getClient().create(HistoryService::class.java).getHistory()
            .enqueue(object :retrofit2.Callback<List<HistoryData>>{

                override fun onResponse(
                    call: Call<List<HistoryData>>,
                    response: Response<List<HistoryData>>
                ) {
                    val historyList=response.body()
                    val myadapter=HistoryAdapter(historyList)

                    recyclerView.adapter=myadapter           //HistoryAdapter(historyList)
                    Toast.makeText(this@MainActivity,"onResponse",Toast.LENGTH_LONG).show()
                    Log.e("BASARILI", call?.request()?.url()?.toString())

                }

                override fun onFailure(call: Call<List<HistoryData>>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"Failure",Toast.LENGTH_LONG).show()
                    Log.e("HATA", call?.request()?.url()?.toString())
                }

                })
    }

    private fun historyItemClicked(historyData: HistoryData) {
        val detailActivityIntent=Intent(this,DetailActivity::class.java)
        detailActivityIntent.putExtra("flight_number",historyData.fligth_number)
        startActivity(detailActivityIntent)

    }

}