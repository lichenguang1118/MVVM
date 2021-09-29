package com.fx.mvvm.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.fx.mvvm.R
import com.fx.mvvm.constants.SpConstants
import com.fx.mvvm.util.SpUtil
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_splash, container, false)
        return root
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            if (SpUtil.getString(SpConstants.TOKEN) != null) {
                Navigation.findNavController(root)
                    .navigate(R.id.action_splashFragment_to_mainFragment)
            } else {
                Navigation.findNavController(root)
                    .navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }
    }
}