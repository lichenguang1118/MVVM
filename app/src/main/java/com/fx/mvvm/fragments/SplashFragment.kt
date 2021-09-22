package com.fx.mvvm.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.fx.mvvm.R
import com.fx.mvvm.base.BaseFragment
import com.fx.mvvm.databinding.FragmentSplashBinding
import com.fx.mvvm.viewmodels.SplashFragmentViewModel
import kotlinx.coroutines.delay


/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : BaseFragment<SplashFragmentViewModel, FragmentSplashBinding>() {


    override val viewModel: SplashFragmentViewModel by viewModels()

    override val layoutResId: Int = R.layout.fragment_splash

    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenResumed {
            delay(2000L)
            Navigation.findNavController(binding.root).navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }

    override fun addListener() {

    }
}