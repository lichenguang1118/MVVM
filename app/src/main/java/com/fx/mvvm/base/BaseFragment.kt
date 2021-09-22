package com.fx.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.fx.mvvm.BR


/**

 * @Author : chenguang

 * @Time : On 2021/9/22 0022 11:01

 * @Description : BaseFragment

 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {

    /** 界面布局 id */
    abstract val layoutResId: Int

    /** 当前界面 ViewModel 对象 */
    protected abstract val viewModel: VM

    /** DataBinding 对象 */
    protected lateinit var binding: DB

    /** 当前界面 Context 对象*/
    protected lateinit var mContext: FragmentActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 保存当前 Context 对象
        mContext = requireActivity()

        // 初始化观察者
        initObserve()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 初始化 DataBinding
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        // 绑定生命周期管理
        binding.lifecycleOwner = this
        // 绑定 ViewModel
        binding.setVariable(BR.viewModel, viewModel)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        addListener()
    }

    abstract fun addListener()


    /**
     * 初始化观察者
     */
    protected open fun initObserve() {
    }
}