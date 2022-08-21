package com.qxy.tiktlin.data.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.qxy.tiktlin.R
import com.qxy.tiktlin.data.netData.RankList

class MovieRankAdapter(): RecyclerView.Adapter<MovieRankAdapter.ViewHolder>(){


    private val MoveRankList = ArrayList<RankList.Data.RankItem>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val MoviePoster: TextView = view.findViewById(R.id.mv_poster)
        val MovieName: TextView = view.findViewById(R.id.mv_name)
        val MovieTag: TextView = view.findViewById(R.id.mv_tag)
        val MovieTime: TextView = view.findViewById(R.id.mv_time)
        val MovieFlane: TextView = view.findViewById(R.id.mv_flane)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_rank,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Movie = MoveRankList[position]
        holder.MoviePoster.text = Movie.poster
        holder.MovieName.text = Movie.name
        holder.MovieTag.text = Movie.tags[0]
        holder.MovieTime.text = Movie.release_data
        holder.MovieFlane.text = Movie.hot.toString()
    }

    override fun getItemCount() = MoveRankList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(content: List<RankList.Data.RankItem>){
        notifyDataSetChanged()
    }
}