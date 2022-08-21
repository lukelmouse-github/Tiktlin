package com.qxy.tiktlin.data.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.qxy.tiktlin.R
import com.qxy.tiktlin.data.netData.RankList


class TvRankAdapter(): RecyclerView.Adapter<TvRankAdapter.ViewHolder>(){

    private val TvRankList = ArrayList<RankList.Data.RankItem>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val TvPoster: TextView = view.findViewById(R.id.tv_poster)
        val TvName: TextView = view.findViewById(R.id.tv_name)
        val TvTime: TextView = view.findViewById(R.id.tv_time)
        val TvFlane: TextView = view.findViewById(R.id.tv_flane)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tv_rank,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Tv = TvRankList[position]
        holder.TvPoster.text = Tv.poster
        holder.TvName.text = Tv.name
        holder.TvTime.text = Tv.release_data
        holder.TvFlane.text = Tv.hot.toString()
    }

    override fun getItemCount() = TvRankList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(content: List<RankList.Data.RankItem>){
        notifyDataSetChanged()
    }
}