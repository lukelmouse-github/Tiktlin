package com.qxy.tiktlin.home.ui



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.qxy.tiktlin.data.netData.Fans
import com.qxy.tiktlin.databinding.ItemFanBinding




class FansAdapter(
    private val lifecycleOwner: LifecycleOwner
) : ListAdapter<Fans.Data.User, FanViewHolder>(FanDiff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FanViewHolder {
        val binding = ItemFanBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            lifecycleOwner = this@FansAdapter.lifecycleOwner
        }
        return FanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FanViewHolder, position: Int) {
        holder.binding.user = getItem(position)
        holder.binding.executePendingBindings()
    }
}

class FanViewHolder(
    internal val binding: ItemFanBinding
) : ViewHolder(binding.root)

object FanDiff : DiffUtil.ItemCallback<Fans.Data.User>() {
    override fun areItemsTheSame(oldItem: Fans.Data.User, newItem: Fans.Data.User): Boolean {
        return oldItem.openId == newItem.openId
    }

    override fun areContentsTheSame(oldItem: Fans.Data.User, newItem: Fans.Data.User): Boolean {
        return oldItem == newItem
    }
}