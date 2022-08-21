package com.qxy.tiktlin.data.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.qxy.tiktlin.R
import com.qxy.tiktlin.data.netData.RankList


class VarietyRankAdapter(): RecyclerView.Adapter<VarietyRankAdapter.ViewHolder>(){

    private val VaRankList = ArrayList<RankList.Data.RankItem>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val VaPoster: TextView = view.findViewById(R.id.va_poster)
        val VaName: TextView = view.findViewById(R.id.va_name)
        val VaTime: TextView = view.findViewById(R.id.va_time)
        val VaFlane: TextView = view.findViewById(R.id.va_flane)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_va_rank,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Va = VaRankList[position]
        holder.VaPoster.text = Va.poster
        holder.VaName.text = Va.name
        holder.VaTime.text = Va.release_data
        holder.VaFlane.text = Va.hot.toString()
    }

    override fun getItemCount() = VaRankList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(content: List<RankList.Data.RankItem>){
        notifyDataSetChanged()
    }
}