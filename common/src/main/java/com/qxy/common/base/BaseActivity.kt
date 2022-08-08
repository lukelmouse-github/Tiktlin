package com.qxy.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.qxy.common.ktx.bindView
import com.qxy.common.ktx.viewLifeCycleOwner
import timber.log.Timber

abstract class BaseActivity<ActBinding : ViewDataBinding>(
    protected @LayoutRes val layout: Int
) : AppCompatActivity(layout) {

    protected lateinit var mBinding: ActBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = bindView<ActBinding>(layout).also {
            it.lifecycleOwner = viewLifeCycleOwner
        }
        Timber.d(javaClass.simpleName)
        initView()
        initConfig()
        initData()
    }

    open fun initView() {

    }

    open fun initConfig() {
    }

    open fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::mBinding.isInitialized) {
            mBinding.unbind()
        }
        Timber.d("onDestroy")
    }
}
