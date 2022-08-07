package com.qxy.common.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.qxy.common.ktx.bindView

abstract class BaseActivity<ActBinding : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var mBinding: ActBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        mBinding = bindView<ActBinding>(getLayoutRes()).also {
            it.lifecycleOwner = this
        }
        initView()
        initConfig()
        initData()
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    open fun initView() {

    }

    open fun initData() {

    }

    open fun initConfig() {

    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::mBinding.isInitialized) {
            mBinding.unbind()
        }
    }
}