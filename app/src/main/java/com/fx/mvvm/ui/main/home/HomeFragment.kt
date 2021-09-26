package com.fx.mvvm.ui.main.home

import android.animation.ValueAnimator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import coil.transform.RoundedCornersTransformation
import com.fx.mvvm.R
import com.fx.mvvm.base.BaseFragment
import com.fx.mvvm.data.network.Resource
import com.fx.mvvm.data.responses.BannerResponse
import com.fx.mvvm.databinding.FragmentHomeBinding
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val layoutResId: Int = R.layout.fragment_home

    override val viewModel: HomeViewModel by viewModels()

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            viewModel.updateHomeView()
        }
    }

    override fun initObserve() {
        super.initObserve()
        viewModel.userInfo.observe(this, {
            when (it) {
                is Resource.Success -> {
                    ValueAnimator.ofInt(0, it.value.data.jgts).apply {
                        addUpdateListener {
                            binding.tvEndOfDay.text = this.animatedValue.toString()
                        }
                        startDelay = 500
                        duration = 1000
                        start()
                    }
                    ValueAnimator.ofInt(
                        0,
                        binding.rlWholeDayOfEnd.width * (it.value.data.jzts - it.value.data.jgts) / it.value.data.jzts
                    ).apply {
                        addUpdateListener {
                            binding.viewEndOfDayCount.layoutParams.width = this.animatedValue as Int
                        }
                        startDelay = 500
                        duration = 1000
                        start()
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        R.string.get_user_info_fail,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        viewModel.bannerList.observe(this, {
            when (it) {
                is Resource.Success -> {
                    binding.banner.setAdapter(object :
                        BannerImageAdapter<BannerResponse>(it.value.data) {
                        override fun onBindView(
                            holder: BannerImageHolder,
                            data: BannerResponse,
                            position: Int,
                            size: Int
                        ) {
                            holder.imageView.load(data.lj) {
                                placeholder(R.mipmap.ic_banner_default)
                                transformations(RoundedCornersTransformation(10f))
                            }
                        }
                    }).also { banner ->
                        banner.addBannerLifecycleObserver(this@HomeFragment)
                        banner.indicator = CircleIndicator(requireContext())
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), R.string.get_banner_fail, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    override fun addListener() {
        binding.tvMainMoreNotification.setOnClickListener {

        }
    }
}