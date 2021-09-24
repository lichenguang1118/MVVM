package com.fx.mvvm.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fx.mvvm.R
import com.fx.mvvm.base.BaseFragment
import com.fx.mvvm.databinding.FragmentHomeBinding
import com.fx.mvvm.ui.main.home.HomeViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment<HomeViewModel,FragmentHomeBinding>() {

    override val layoutResId: Int = R.layout.fragment_home

    override val viewModel: HomeViewModel by viewModels()



}