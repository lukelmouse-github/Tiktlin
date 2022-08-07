package com.qxy.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

// Todo Fragment 封装
abstract class BaseFragment : Fragment() {
    private var mBinding: ViewDataBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding

    open fun initConfig() {

    }

    open fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding?.unbind()
    }
}