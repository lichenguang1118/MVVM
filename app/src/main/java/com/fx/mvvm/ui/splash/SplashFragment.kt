package com.fx.mvvm.ui.splash

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.fx.mvvm.R
import com.fx.mvvm.base.BaseFragment
import com.fx.mvvm.constants.SpConstants
import com.fx.mvvm.databinding.FragmentSplashBinding
import com.fx.mvvm.util.SpUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : BaseFragment<SplashFragmentViewModel, FragmentSplashBinding>() {

    override val viewModel: SplashFragmentViewModel by viewModels()

    override val layoutResId: Int = R.layout.fragment_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(),R.color.white)
    }

    override fun initObserve() {
        lifecycleScope.launch {
            delay(2000L)
            if (SpUtil.getString(SpConstants.TOKEN) != null) {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_splashFragment_to_mainFragment)
            } else {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }
    }

}