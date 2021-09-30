package com.fx.mvvm.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fx.mvvm.base.BaseViewModel
import com.fx.mvvm.data.network.Resource
import com.fx.mvvm.data.repository.LoginRepository
import com.fx.mvvm.data.responses.BaseResponse
import com.fx.mvvm.data.responses.TokenResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

/**

 * @Author : chenguang

 * @Time : On 2021/9/22 0022 15:54

 * @Description : LoginViewModel

 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : BaseViewModel() {

    private val _loginResponse: MutableLiveData<Resource<BaseResponse<Boolean>>> = MutableLiveData()
    private val _userTokenResponse: MutableLiveData<Resource<BaseResponse<TokenResponse>>> = MutableLiveData()
    val loginResponse: LiveData<Resource<BaseResponse<Boolean>>> = _loginResponse
    val userTokenResponse: LiveData<Resource<BaseResponse<TokenResponse>>> = _userTokenResponse

    val idNumber: MutableLiveData<String> = MutableLiveData("")

    val valid: MutableLiveData<Boolean> = MutableLiveData(false)

    val showProgressBar: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkUser() {
        showProgressBar.value = true
        viewModelScope.launch {
            _loginResponse.value = repository.checkUser(idNumber.value.toString())
        }
    }

    fun getUserToken() {
        viewModelScope.launch {
            _userTokenResponse.value = repository.getUserToken(
                createJSONObject().put("sfzh", idNumber.value.toString()).toString()
                    .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            )
        }
    }

}
