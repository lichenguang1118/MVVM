package com.fx.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fx.mvvm.base.BaseViewModel
import com.fx.mvvm.network.Resource
import com.fx.mvvm.repository.LoginRepository
import com.fx.mvvm.responses.BaseResponse
import kotlinx.coroutines.launch

/**

 * @Author : chenguang

 * @Time : On 2021/9/22 0022 15:54

 * @Description : LoginViewModel

 */
class LoginViewModel(
    private val repository: LoginRepository
) : BaseViewModel() {

    private val _loginResponse :MutableLiveData<Resource<BaseResponse>> = MutableLiveData()
    val loginResponse : LiveData<Resource<BaseResponse>> = _loginResponse

    val idNumber: MutableLiveData<String> = MutableLiveData("")

    val valid: MutableLiveData<Boolean> = MutableLiveData(false)

    val showProgressBar: MutableLiveData<Boolean> = MutableLiveData(false)

    suspend fun login(){
        viewModelScope.launch {
            _loginResponse.value = repository.checkUser(idNumber.toString())
        }
    }

}