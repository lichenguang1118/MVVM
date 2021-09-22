package com.fx.mvvm.base

import androidx.lifecycle.ViewModel
import com.fx.common.util.LogUtil

/**

 * @Author : chenguang

 * @Time : On 2021/9/22 0022 10:12

 * @Description : BaseViewModel

 */
abstract class BaseViewModel : ViewModel(){

    override fun onCleared() {
        LogUtil.e("View onCleared ----> ViewModel: $this")
    }

}