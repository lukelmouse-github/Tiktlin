package com.qxy.tiktlin.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.qxy.tiktlin.common.ktx.bindView
import com.qxy.tiktlin.common.ktx.viewLifeCycleOwner
import timber.log.Timber

abstract class BaseActivity<Binding : ViewDataBinding>(
    @LayoutRes protected val layout: Int
) : AppCompatActivity(layout) {

    protected lateinit var binding: Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindView<Binding>(layout).also {
            it.lifecycleOwner = viewLifeCycleOwner
        }
        Timber.d(javaClass.simpleName)
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
