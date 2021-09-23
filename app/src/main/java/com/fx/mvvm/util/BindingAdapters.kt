package com.fx.mvvm.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**

 * @Author : chenguang

 * @Time : On 2021/9/23 0023 9:54

 * @Description : BindingAdapters

 */
object BindingAdapters {

    @BindingAdapter("android:showOrHide")
    @JvmStatic fun showOrHide(view: View, showOrHide: Boolean) {
        if (showOrHide) {
            view.visibility = View.VISIBLE
        }else{
            view.visibility = View.GONE
        }
    }

}