package com.qxy.tiktlin.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.drake.logcat.LogCat
import com.qxy.tiktlin.common.ktx.bindView
import com.qxy.tiktlin.common.ktx.immediateStatusBar
import com.qxy.tiktlin.common.ktx.viewLifeCycleOwner

abstract class BaseActivity<Binding : ViewDataBinding>(
    @LayoutRes protected val layout: Int
) : AppCompatActivity(layout) {

    protected lateinit var binding: Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 沉浸式布局
        immediateStatusBar()
        binding = bindView<Binding>(layout).also {
            it.lifecycleOwner = viewLifeCycleOwner
        }
        LogCat.d(javaClass.simpleName)
        initConfig()
        initView()
        initData()
    }

    open fun initConfig() {
    }

    open fun initView() {
    }

    open fun initData() {
    }
}
