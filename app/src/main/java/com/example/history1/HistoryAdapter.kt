package com.example.history1

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(
    var historyList: List<HistoryData>? ):RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        var inflater=LayoutInflater.from(parent.context)
        var tekSatirHistory=inflater.inflate(R.layout.tek_satir,parent,false)

        return  HistoryViewHolder(tekSatirHistory)
    }

    override fun getItemCount(): Int {
        return historyList!!.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        var tHistory = historyList?.get(position)
        holder?.setData(tHistory, position)
    }

    inner class HistoryViewHolder(viewGroup: View):RecyclerView
    .ViewHolder(viewGroup){

        var satir=viewGroup as CardView
         val textTitle=itemView.findViewById<TextView>(R.id.tvTitle)
         val textDate=itemView.findViewById<TextView>(R.id.tvDate)
        val textDetails=itemView.findViewById<TextView>(R.id.tvDetail)

        fun setData(tHistory:HistoryData?, position: Int){
            textTitle.text=tHistory?.title
            textDetails.text=tHistory?.details
            textDate.text=tHistory?.event_date_utc
            if(tHistory?.flight_number!! >0 ) {      // DetailActivity'de Flight_number'a göre sorgu yaptığımız için,
                // eğer flight_number yoksa DetailActivity'e bağlanmıyor.Bağlantı yapmaya çalışmaması için kontrol koydum.

                //id=10 ve id=13 flight_number=null olmasına rağmen başka id'nin detailActivity'si açılıyor ??
                
                satir.setOnClickListener { v ->        

                    var intent = Intent(v.context, DetailActivity::class.java)
                    intent.putExtra("flight_number", tHistory?.flight_number)
                    v.context.startActivity(intent)

                }
            }

        }

    }


}