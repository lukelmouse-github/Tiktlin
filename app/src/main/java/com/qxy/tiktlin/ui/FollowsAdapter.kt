package com.qxy.tiktlin.home.ui



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.qxy.tiktlin.data.netData.Follows
import com.qxy.tiktlin.databinding.ItemFollowBinding


class FollowsAdapter(
    private val lifecycleOwner: LifecycleOwner
) : ListAdapter<Follows.Data.User, FollowViewHolder>(FollowDiff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        val binding = ItemFollowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            lifecycleOwner = this@FollowsAdapter.lifecycleOwner
        }
        return FollowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        holder.binding.user = getItem(position)
        holder.binding.executePendingBindings()
    }
}

class FollowViewHolder(
    internal val binding: ItemFollowBinding
) : ViewHolder(binding.root)

object FollowDiff : DiffUtil.ItemCallback<Follows.Data.User>() {
    override fun areItemsTheSame(oldItem: Follows.Data.User, newItem: Follows.Data.User): Boolean {
        return oldItem.openId == newItem.openId
    }

    override fun areContentsTheSame(oldItem: Follows.Data.User, newItem: Follows.Data.User): Boolean {
        return oldItem == newItem
    }
}