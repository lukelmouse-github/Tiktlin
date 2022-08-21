package com.qxy.tiktlin.widget

import android.util.Log
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

    private lateinit var onVideoClickListener : OnVideoClickListener

    private val videoList = ArrayList<VideoList.Video>()

    fun setVideoClickListener(onVideoClickListener : OnVideoClickListener){
        this.onVideoClickListener = onVideoClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_works_video,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val worksVideo = videoList[position]
        holder.setData(worksVideo)
        holder.itemView.setOnClickListener {
            Log.d("TAG", "abc onItemClick: " + videoList[position].item_id + videoList[position].title)
            onVideoClickListener.onItemClick(videoList[position])
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    fun setData(content : List<VideoList.Video>){
        videoList.clear()
        videoList.addAll(content)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        lateinit var video : VideoList.Video

        val worksVideoImg : ImageView = view.findViewById(R.id.img_works_video)

        fun setData(worksVideo: VideoList.Video) {
            val cover = worksVideo.cover
            video = worksVideo
            Glide.with(itemView.context).load(cover).into(worksVideoImg)
        }

    }

    interface OnVideoClickListener {
        fun onItemClick(video: VideoList.Video)
    }

}