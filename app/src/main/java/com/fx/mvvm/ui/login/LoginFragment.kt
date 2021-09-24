package com.fx.mvvm.ui.login

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.fx.common.util.StringUtil
import com.fx.mvvm.R
import com.fx.mvvm.base.BaseFragment
import com.fx.mvvm.databinding.FragmentLoginBinding
import com.fx.mvvm.data.network.Resource
import com.fx.mvvm.viewmodels.LoginViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    override val viewModel: LoginViewModel by viewModels()

    override val layoutResId: Int = R.layout.fragment_login


    override fun initObserve() {

        viewModel.run {
            idNumber.observe(this@LoginFragment, {
                viewModel.valid.postValue(StringUtil.isIDCard(it))
            })

            loginResponse.observe(this@LoginFragment, {
                when (it) {
                    is Resource.Success -> {
                        if (it.value.data == true) {
                            Navigation.findNavController(binding.root)
                                .navigate(R.id.action_loginFragment_to_mainFragment)
                        }
                    }
                    is Resource.Failure -> {
                        Toast.makeText(
                            requireContext(),
                            it.errorBody.toString(),
                            Toast.LENGTH_SHORT
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