package com.qxy.tiktlin.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<Binding : ViewDataBinding>(
    @LayoutRes protected val layout: Int
) : Fragment(layout) {

    private var _binding: Binding? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initConfig()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layout, container, false)
        initView()
        initData()
        return binding.root
    }

    open fun initConfig() {
    }

    open fun initView() {
    }

    open fun initData() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
