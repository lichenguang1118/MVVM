package com.fx.mvvm.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fx.mvvm.base.BaseViewModel
import com.fx.mvvm.data.network.Resource
import com.fx.mvvm.data.repository.HomeRepository
import com.fx.mvvm.data.responses.BannerResponse
import com.fx.mvvm.data.responses.BaseResponse
import com.fx.mvvm.data.responses.UserInfoResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
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

    private lateinit var requestBody: RequestBody

    private val _userInfo :MutableLiveData<Resource<BaseResponse<UserInfoResponse>>> = MutableLiveData()

    val userInfo : LiveData<Resource<BaseResponse<UserInfoResponse>>> = _userInfo

    private val _bannerList : MutableLiveData<Resource<BaseResponse<List<BannerResponse>>>> = MutableLiveData()

    val bannerList : LiveData<Resource<BaseResponse<List<BannerResponse>>>> = _bannerList

    init {
        updateHomeView()
    }

    fun updateHomeView() = viewModelScope.launch {
        requestBody = createRequestParames().toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        _userInfo.value = repository.getUserInfo(requestBody)
        _bannerList.value = repository.getBannerList(requestBody)
    }

}