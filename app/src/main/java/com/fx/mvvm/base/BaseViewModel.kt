package com.fx.mvvm.base

import androidx.lifecycle.ViewModel
import com.fx.mvvm.constants.SpConstants
import com.fx.mvvm.util.LogUtil
import com.fx.mvvm.util.SpUtil
import org.json.JSONObject

/**

 * @Author : chenguang

 * @Time : On 2021/9/22 0022 10:12

 * @Description : BaseViewModel

 */
abstract class BaseViewModel : ViewModel(){

    override fun onCleared() {
        LogUtil.e("View onCleared ----> ViewModel: $this")
    }

    companion object {

        fun createJSONObject() : JSONObject{
            return JSONObject()
        }

        fun createRequestParames() : JSONObject{
            return JSONObject().apply {
                put(SpConstants.TOKEN, SpUtil.getString(SpConstants.TOKEN))
                put(SpConstants.SIPID, SpUtil.getString(SpConstants.SIPID))
            }
        }
    }



}