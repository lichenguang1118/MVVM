package com.fx.mvvm.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.fx.mvvm.R
import com.fx.mvvm.base.BaseFragment
import com.fx.mvvm.databinding.FragmentNotificationBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class NotificationFragment : BaseFragment<NotificationViewModel, FragmentNotificationBinding>() {

    private val tabTitle = intArrayOf(R.string.today, R.string.one_week, R.string.all)

    override val layoutResId: Int = R.layout.fragment_notification

    override val viewModel: NotificationViewModel by viewModels()

    val args: NotificationFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.include.tvTitleMain.text = args.title
    }

    override fun initView() {
        binding.notificationViewPager.apply {
            offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
            adapter = object : FragmentStateAdapter(childFragmentManager, lifecycle) {
                override fun getItemCount(): Int {
                    return 3
                }

                override fun createFragment(position: Int): Fragment {
                    val fragment = MessageFragment()
                    val bundle = Bundle()
                    bundle.putInt("type", 2 - position)
                    bundle.putInt("parentType", args.type)
                    fragment.arguments = bundle
                    return fragment
                }
            }
        }
        TabLayoutMediator(
            binding.notificationTab, binding.notificationViewPager, true, false
        ) { tab: TabLayout.Tab, position: Int ->
            tab.setText(tabTitle[position])
        }.attach()
    }
}