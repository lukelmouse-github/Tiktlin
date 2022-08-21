package com.qxy.tiktlin.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.qxy.tiktlin.R
import com.qxy.tiktlin.data.netData.RankList
import com.qxy.tiktlin.util.DateUtils
import com.qxy.tiktlin.widget.appInstance

class MovieAdapter(private val dataSet: List<RankList.Data.RankItem>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coverImg = view.findViewById<ImageView>(R.id.coverImg)
        val movieName = view.findViewById<TextView>(R.id.name)
        val movieType = view.findViewById<TextView>(R.id.movieType)
        val relaseDate = view.findViewById<TextView>(R.id.movieType)
        val hotName = view.findViewById<TextView>(R.id.hotNum)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = dataSet[position]
        holder.movieName.text = movie.name
        holder.movieType.text = movie.tags.take(3).joinToString { " / " }
        Glide.with(holder.coverImg.context).load(movie.poster).into(holder.coverImg)
        holder.relaseDate.text = movie.release_data
        holder.hotName.text = DateUtils.transHotNum(movie.discussion_hot)

        val drawable = appInstance.getDrawable(R.drawable.hot)
        drawable?.setBounds(0, 0, 50, 50).apply {
            holder.hotName.setCompoundDrawables(drawable, null, null, null)
        }
    }

    override fun getItemCount(): Int = dataSet.size



}