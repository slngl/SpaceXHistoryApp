package com.example.history1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(
    historyList: List<HistoryData>? , var  onHistoryListener : OnHistoryListener):RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {


    var historyList=historyList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return  HistoryViewHolder(parent,onHistoryListener)
    }

    override fun getItemCount(): Int {
        return historyList!!.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bindTo(historyList!!.get(position))

    }
    interface OnHistoryListener{
        fun onHistoryClick(position:Int)
    }
    inner class HistoryViewHolder(viewGroup: ViewGroup, var onHistoryListener: OnHistoryListener):RecyclerView
    .ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.tek_satir,viewGroup,false)), View.OnClickListener{
        lateinit var onClickListener:View.OnClickListener
        var satir=viewGroup as RecyclerView
        private val textTitle=itemView.findViewById<TextView>(R.id.tvTitle)
        private val textDate=itemView.findViewById<TextView>(R.id.tvDate)
        private val textDetails=itemView.findViewById<TextView>(R.id.tvDetail)

        fun bindTo(historyData: HistoryData){
            textTitle.text=historyData.title
            textDate.text=historyData.event_date_utc
            textDetails.text=historyData.details

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onHistoryListener.onHistoryClick(adapterPosition)
        }

    }


}