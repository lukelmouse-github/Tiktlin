package com.qxy.tiktlin.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.qxy.tiktlin.R
import com.qxy.tiktlin.data.netData.VideoList

/**
 * @Description:
 * @Package com.qxy.tiktlin.widget
 * @Author: immorts
 * @CreateTime: 2022/8/22 12:10 上午
 */
class VideoListAdapter() :
    RecyclerView.Adapter<VideoListAdapter.ViewHolder>() {

    private val videoList = ArrayList<VideoList.Video>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_works_video,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val worksVideo = videoList[position]
        holder.setData(worksVideo)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    fun setData(content : List<VideoList.Video>){
        videoList.clear()
        videoList.addAll(content)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val worksVideoImg : ImageView = view.findViewById(R.id.img_works_video)

        fun setData(worksVideo: VideoList.Video) {
            val cover = worksVideo.cover
            Glide.with(itemView.context).load(cover).into(worksVideoImg)
        }
    }

}