package com.qxy.tiktlin.ui

import android.content.Context
import android.os.Bundle
import com.qxy.tiktlin.R
import com.qxy.tiktlin.databinding.FragmentVideoSimpleBinding
import com.qxy.tiktlin.widget.BaseFragment

class VideoSimpleFragment(): BaseFragment<FragmentVideoSimpleBinding>(R.layout.fragment_video_simple) {
    private var tagName: String = "作品"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString("tagName")?.let {
            tagName = it
        }
    }
    override fun initView() {
        super.initView()
        binding.tagName.text = tagName
    }

    override fun initData() {
        super.initData()
    }

    companion object {
        @JvmStatic
        fun newInstance(tagName: String) = VideoSimpleFragment().apply {
            arguments = Bundle().apply {
                putString("tagName", tagName)
            }
        }
    }
}