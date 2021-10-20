package com.fx.mvvm.ui.test

import androidx.activity.viewModels
import com.fx.mvvm.R
import com.fx.mvvm.base.BaseActivity
import com.fx.mvvm.databinding.ActivityTestBinding

class TestActivity : BaseActivity<TestViewModel, ActivityTestBinding>() {

    override val layoutResId: Int = R.layout.activity_test

    override val viewModel: TestViewModel by viewModels()

    override fun initView() {
        binding.textView4.text = "测试测试"
    }

}