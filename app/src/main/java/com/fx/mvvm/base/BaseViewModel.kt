package com.fx.mvvm.base

import androidx.lifecycle.ViewModel
import com.fx.mvvm.util.LogUtil
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap

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
    }



}