package com.qxy.tiktlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.qxy.tiktlin.R
import com.qxy.tiktlin.databinding.FragmentMeBothBinding


class MeBothFragment : Fragment(){

   private  var _binding: FragmentMeBothBinding? = null

    private val binding get() = _binding!!

    val args: MeBothFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMeBothBinding.inflate(inflater, container, false).apply {}
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val meBothAdapter=MeBothAdapter(this)
        meBothAdapter.notifyItemInserted(args.pageId)
        binding.viewpager.adapter = meBothAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = resources.getString(INFO_TITLES[position])
        }.attach()

        binding.tabLayout.post {
            binding.tabLayout.selectTab(binding.tabLayout.getTabAt(args.pageId))
            binding.viewpager.post {
                binding.viewpager.setCurrentItem(args.pageId)
                binding.viewpager.requestTransform()
            }
        }
    }


    inner class MeBothAdapter(fragment: Fragment) :  FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int) = INFO_PAGES[position].invoke()
        override fun getItemCount() = INFO_PAGES.size
    }

    companion object {
        private val INFO_PAGES = arrayOf(
            { FansFragment() },
            { FollowsFranment() }
        )
        private val INFO_TITLES = arrayOf(
            R.string.me_label_fans,
            R.string.me_label_followers
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}