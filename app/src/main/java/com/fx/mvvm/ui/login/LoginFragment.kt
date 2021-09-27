package com.fx.mvvm.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.fx.mvvm.util.SpUtil
import com.fx.mvvm.util.StringUtil
import com.fx.mvvm.R
import com.fx.mvvm.base.BaseFragment
import com.fx.mvvm.constants.NetWordConfig
import com.fx.mvvm.constants.SpConstants
import com.fx.mvvm.data.network.Resource
import com.fx.mvvm.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    override val viewModel: LoginViewModel by viewModels()

    override val layoutResId: Int = R.layout.fragment_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(),R.color.app_color)
    }

    override fun initObserve() {

        viewModel.run {
            idNumber.observe(this@LoginFragment, {
                viewModel.valid.postValue(StringUtil.isIDCard(it))
            })

            loginResponse.observe(this@LoginFragment, {
                when (it) {
                    is Resource.Success -> {
                        if (it.value.data) {
                            viewModel.getUserToken()
                        } else {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.invalid_id_number),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    is Resource.Failure -> {
                        viewModel.showProgressBar.postValue(false)
                        Toast.makeText(
                            requireContext(), it.errorBody.toString(), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })

            userTokenResponse.observe(this@LoginFragment, Observer {
                viewModel.showProgressBar.postValue(false)
                when (it) {
                    is Resource.Success -> {
                        if (it.value.code == NetWordConfig.REQUEST_SUCCESS) {
                            SpUtil.put(SpConstants.TOKEN, it.value.data.token)
                            SpUtil.put(SpConstants.SIPID,it.value.data.sipId)
                            Navigation.findNavController(binding.root)
                                .navigate(R.id.action_loginFragment_to_mainFragment)
                        } else {
                            Toast.makeText(
                                requireContext(), it.value.msg, Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    is Resource.Failure -> {
                        Toast.makeText(
                            requireContext(), it.errorBody.toString(), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        }

    }

    override fun addListener() {
        binding.ivFaceAuthentication.setOnClickListener {
            viewModel.login()
        }
    }

}