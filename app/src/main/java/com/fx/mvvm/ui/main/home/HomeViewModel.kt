package com.fx.mvvm.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fx.mvvm.base.BaseViewModel
import com.fx.mvvm.data.network.Resource
import com.fx.mvvm.data.repository.HomeRepository
import com.fx.mvvm.data.responses.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

/**

 * @Author : chenguang

 * @Time : On 2021/9/24 0024 17:45

 * @Description : HomeViewModel

 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : BaseViewModel() {

    private lateinit var requestBody1: RequestBody
    private lateinit var requestBody2: RequestBody

    private val _userInfo: MutableLiveData<Resource<BaseResponse<UserInfoResponse>>> =
        MutableLiveData()

    val userInfo: LiveData<Resource<BaseResponse<UserInfoResponse>>> = _userInfo

    private val _bannerList: MutableLiveData<Resource<BaseResponse<List<BannerResponse>>>> =
        MutableLiveData()

    val bannerList: LiveData<Resource<BaseResponse<List<BannerResponse>>>> = _bannerList

    private val _callPoliceList: MutableLiveData<Resource<BaseResponse<List<CallPoliceResponse>>>> =
        MutableLiveData()

    val callPoliceList: LiveData<Resource<BaseResponse<List<CallPoliceResponse>>>> = _callPoliceList

    private val _notificationList: MutableLiveData<Resource<BaseResponse<List<NotificationResponse>>>> =
        MutableLiveData()

    val notificationList: LiveData<Resource<BaseResponse<List<NotificationResponse>>>> =
        _notificationList

    init {
        updateHomeView()
    }

    fun updateHomeView() = viewModelScope.launch {
        requestBody1 = createRequestParames().toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

        _userInfo.value = repository.getUserInfo(requestBody1)
        _bannerList.value = repository.getBannerList(requestBody1)

        requestBody2 =
            createRequestParames().put("page", 1).put("rows", 6).put("type", 1).toString()
                .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

        _callPoliceList.value = repository.getPageCallPolice(requestBody2)

        _notificationList.value = repository.getPageMessage(requestBody2)
    }

}