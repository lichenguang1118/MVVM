package com.fx.mvvm.ui.main.home

import android.animation.ValueAnimator
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.RoundedCornersTransformation
import com.fx.mvvm.R
import com.fx.mvvm.base.BaseFragment
import com.fx.mvvm.constants.NetWordConfig
import com.fx.mvvm.data.network.Resource
import com.fx.mvvm.data.responses.BannerResponse
import com.fx.mvvm.data.responses.CallPoliceResponse
import com.fx.mvvm.databinding.FragmentHomeBinding
import com.fx.mvvm.ui.main.MainFragmentDirections
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    companion object {
        private const val TAG: String = "HomeFragment"
    }

    override val layoutResId: Int = R.layout.fragment_home

    override val viewModel: HomeViewModel by viewModels()

    private var callPoliceAdapter: CallPoliceAdapter = CallPoliceAdapter()

    private val notificationAdapter: NotificationAdapter = NotificationAdapter()

    private lateinit var navHostFragment: NavHostFragment

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            viewModel.updateHomeView()
        }
    }

    override fun initView() {
        binding.recyclerMainWarn.adapter = callPoliceAdapter

        binding.recyclerMainNotification.adapter = notificationAdapter

        navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

    }

    override fun initObserve() {
        super.initObserve()
        viewModel.userInfo.observe(this, {
            when (it) {
                is Resource.Success -> {
                    if (it.value.code == NetWordConfig.REQUEST_SUCCESS) {
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
                                binding.viewEndOfDayCount.layoutParams.width =
                                    this.animatedValue as Int
                            }
                            startDelay = 500
                            duration = 1000
                            start()
                        }
                    } else {
                        Toast.makeText(requireContext(), it.value.msg, Toast.LENGTH_SHORT).show()
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

        viewModel.callPoliceList.observe(this, {
            when (it) {
                is Resource.Success -> {
                    if (it.value.code == NetWordConfig.REQUEST_SUCCESS) {
                        val list: List<CallPoliceResponse> = it.value.data
                        if (list.isNotEmpty()) {
                            binding.viewExcellent.visibility = View.GONE
                            callPoliceAdapter.update(list)
                        } else {
                            binding.viewExcellent.visibility = View.VISIBLE
                        }
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), it.errorBody.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

        viewModel.notificationList.observe(this, {
            when (it) {
                is Resource.Success -> {
                    if (it.value.code == NetWordConfig.REQUEST_SUCCESS) {
                        val list = it.value.data
                        if (list.isNotEmpty()) {
                            notificationAdapter.update(list)
                        } else {

                        }
                    } else {
                        Toast.makeText(requireContext(), it.value.msg, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), it.errorBody.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    override fun addListener() {
        binding.tvMainMoreNotification.setOnClickListener {

        }

        binding.tvTodayPerformance.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToNotificationFragment(
                getString(R.string.today_performance),
                0
            )
            navHostFragment.findNavController().navigate(action)
        }

        binding.tvMainMoreNotification.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToNotificationFragment(
                getString(R.string.notification_message),
                1
            )
            navHostFragment.findNavController().navigate(action)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG, "onDestroyView: ")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: ")
    }
}