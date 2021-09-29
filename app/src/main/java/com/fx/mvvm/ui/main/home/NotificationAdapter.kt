package com.fx.mvvm.ui.main.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fx.mvvm.data.responses.NotificationResponse
import com.fx.mvvm.databinding.ItemMainNotificationBinding

/**

 * @Author : chenguang

 * @Time : On 2021/9/29 0029 14:18

 * @Description : NotificationAdapter

 */
class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    private var data: List<NotificationResponse> = emptyList()

    inner class ViewHolder(private val binding: ItemMainNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NotificationResponse) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMainNotificationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<NotificationResponse>) {
        this.data = data
        notifyDataSetChanged()
    }
}