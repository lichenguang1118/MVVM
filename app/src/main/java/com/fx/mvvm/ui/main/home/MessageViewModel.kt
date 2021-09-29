package com.fx.mvvm.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fx.mvvm.base.BaseViewModel
import com.fx.mvvm.data.network.Resource
import com.fx.mvvm.data.repository.HomeRepository
import com.fx.mvvm.data.responses.BaseResponse
import com.fx.mvvm.data.responses.CallPoliceResponse
import com.fx.mvvm.data.responses.NotificationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

/**

 * @Author : chenguang

 * @Time : On 2021/9/29 0029 16:42

 * @Description : MessageViewModel

 */
@HiltViewModel
class MessageViewModel @Inject constructor(
    private val repository: HomeRepository
) : BaseViewModel() {

    private lateinit var requestBody: RequestBody

    private val _callPoliceList: MutableLiveData<Resource<BaseResponse<List<CallPoliceResponse>>>> =
        MutableLiveData()

    val callPoliceList: LiveData<Resource<BaseResponse<List<CallPoliceResponse>>>> = _callPoliceList

    private val _notificationList: MutableLiveData<Resource<BaseResponse<List<NotificationResponse>>>> =
        MutableLiveData()

    val notificationList: LiveData<Resource<BaseResponse<List<NotificationResponse>>>> =
        _notificationList


    fun updateView(parentType: Int, type: Int, page: Int) = viewModelScope.launch {
        requestBody =
            createRequestParames().put("page", page).put("rows", 10).put("type", type).toString()
                .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        when (parentType) {
            0 -> {
                _callPoliceList.value = repository.getPageCallPolice(requestBody)
            }
            1 -> {
                _notificationList.value = repository.getPageMessage(requestBody)
            }
        }
    }

}