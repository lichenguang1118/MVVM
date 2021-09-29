package com.fx.mvvm.ui.main.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fx.mvvm.data.responses.CallPoliceResponse
import com.fx.mvvm.databinding.ItemMainWarmBinding
import com.fx.mvvm.util.DateUtil

/**

 * @Author : chenguang

 * @Time : On 2021/9/27 0027 15:06

 * @Description : CallPoliceAdapter

 */
class CallPoliceAdapter : RecyclerView.Adapter<CallPoliceAdapter.MyViewHolder>() {

    private var data = emptyList<CallPoliceResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemMainWarmBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data[position]
        when (item.bjzt) {
            1 -> {
                item.content = "报警一次，报警原因：无信号报警"
            }
            2 -> {
                item.content = "报警一次，报警原因：禁出越界报警"
            }
            3 -> {
                item.content = "报警一次，报警原因：禁入越界报警"
            }
            8 -> {
                item.content = "报警一次，报警原因：人机分离报警"
            }
            16 -> {
                item.content = "报警一次，报警原因：手环拆卸报警"
            }
            32 -> {
                item.content = "报警一次，报警原因：低电报警"
            }
            else -> {
                item.content = "报警一次，报警原因：无"
            }
        }
        item.timeString = DateUtil.convertTimeStamp(item.bjTime)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<CallPoliceResponse>) {
        this.data = data
        notifyDataSetChanged()
    }


    inner class MyViewHolder(private val binding: ItemMainWarmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CallPoliceResponse) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

}