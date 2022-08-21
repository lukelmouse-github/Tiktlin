package com.qxy.tiktlin.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.drake.logcat.LogCat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.qxy.tiktlin.databinding.ItemVideoBinding
import com.qxy.tiktlin.ui.vm.VideoDetailViewModel

class VideoPagerAdapter(
    private val viewModel: VideoDetailViewModel
) : RecyclerView.Adapter<VideoPagerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            Glide.with(itemView.context)
                .load(viewModel.videos[position].video.cover)
                .into(binding.cover)

            binding.viewData = viewModel.viewDatum[position]
            var player = viewModel.players[position]
            if (player == null) {
                player = ExoPlayer.Builder(itemView.context).build()
                viewModel.players[position] = player
                player.repeatMode = ExoPlayer.REPEAT_MODE_ALL
                val url = viewModel.videos[position].url
                if (url != null) player.setMediaItem(MediaItem.fromUri(url))
                player.prepare()
                player.play()

            }
            binding.player.player = player
            LogCat.d("Bound $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.player.controllerAutoShow = false
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount() = viewModel.videos.size
}
