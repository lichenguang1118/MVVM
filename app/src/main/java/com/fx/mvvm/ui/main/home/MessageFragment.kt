package com.fx.mvvm.ui.main.home

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.fx.mvvm.R
import com.fx.mvvm.base.BaseFragment
import com.fx.mvvm.constants.NetWordConfig
import com.fx.mvvm.data.network.Resource
import com.fx.mvvm.data.responses.CallPoliceResponse
import com.fx.mvvm.databinding.FragmentMessageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageFragment : BaseFragment<MessageViewModel, FragmentMessageBinding>() {

    override val layoutResId: Int = R.layout.fragment_message

    override val viewModel: MessageViewModel by viewModels()

    private lateinit var notificationAdapter: NotificationAdapter

    private lateinit var callPoliceAdapter: CallPoliceAdapter

    private var type: Int = 0
    private var parentType: Int = 0

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        type = args!!.getInt("type")
        when (type) {
            0 -> {
                type = 0
            }
            1 -> {
                type = 2
            }
            2 -> {
                type = 1
            }
        }
        parentType = args.getInt("parentType")
    }

    override fun initView() {
        super.initView()
        if (parentType == 0) {
            callPoliceAdapter = CallPoliceAdapter()
            binding.recyclerMessage.adapter = callPoliceAdapter
        } else {
            notificationAdapter = NotificationAdapter()
            binding.recyclerMessage.adapter = notificationAdapter
        }
        viewModel.updateView(parentType, type, 1)
    }

    override fun initObserve() {
        viewModel.callPoliceList.observe(this, {
            when (it) {
                is Resource.Success -> {
                    if (it.value.code == NetWordConfig.REQUEST_SUCCESS) {
                        val list: List<CallPoliceResponse> = it.value.data
                        if (list.isNotEmpty()) {
                            callPoliceAdapter.update(list)
                        } else {
                            // TODO: 2021/9/29 0029 添加无数据
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
                            // TODO: 2021/9/29 0029 添加无数据
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
}