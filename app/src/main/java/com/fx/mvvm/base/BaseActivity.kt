package com.fx.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.fx.mvvm.BR

/**

 * @Author : chenguang

 * @Time : On 2021/10/20 0020 10:05

 * @Description : BaseActivity

 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    /** 界面布局 id */
    abstract val layoutResId: Int

    /** DataBinding 对象 */
    protected lateinit var binding: DB

    /** 当前界面 ViewModel 对象 */
    protected abstract val viewModel: VM

    private lateinit var mContext: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //保存当前 Context 对象
        mContext = this
        setContentView(layoutResId)

        initView()

        // 初始化观察者
        initObserve()
    }


    override fun setContentView(layoutResID: Int) {
        // 初始化 DataBinding
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            layoutResID, null, false
        )

        // 绑定生命周期管理
        binding.lifecycleOwner = this

        // 绑定 ViewModel
        binding.setVariable(BR.viewModel, viewModel)

        // 设置布局
        super.setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        addListener()
    }

    protected open fun initView() {}

    /**
     * 初始化观察者
     */
    protected open fun initObserve() {}

    /**
     * 初始化listener
     */
    protected open fun addListener() {}


}