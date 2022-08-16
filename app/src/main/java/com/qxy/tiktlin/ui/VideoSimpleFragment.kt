package com.qxy.tiktlin.fragment

import com.qxy.tiktlin.R
import com.qxy.tiktlin.databinding.FragmentVideoSimpleBinding
import com.qxy.tiktlin.widget.BaseFragment

class VideoSimpleFragment(val tagName: String): BaseFragment<FragmentVideoSimpleBinding>(R.layout.fragment_video_simple) {

    override fun initView() {
        super.initView()
        binding.tagName.text = tagName
    }

    override fun initData() {
        super.initData()
    }
}