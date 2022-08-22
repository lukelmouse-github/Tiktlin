package com.qxy.tiktlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnNextLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.qxy.tiktlin.data.netData.Follows
import com.qxy.tiktlin.databinding.FragmentMeFollowsBinding
import com.qxy.tiktlin.home.ui.FollowsAdapter
import com.qxy.tiktlin.ui.vm.FollowsViewModel
import com.qxy.tiktlin.util.setContentMaxWidth
import com.qxy.tiktlin.widget.executeAfter
import com.qxy.tiktlin.widget.launchAndRepeatWithViewLifecycle
import kotlinx.coroutines.launch

class FollowsFranment: Fragment() {
    private var _binding: FragmentMeFollowsBinding? = null

    private val binding get() = _binding!!

    private val followsViewModel: FollowsViewModel by viewModels()

    private lateinit var homeRecyclerView: RecyclerView

    private lateinit var followsAdapter: FollowsAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel: FollowsViewModel by viewModels()

        _binding = FragmentMeFollowsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = homeViewModel
        }

        homeRecyclerView = binding.recyclerviewFollows

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Session list configuration
        followsAdapter = FollowsAdapter(
            viewLifecycleOwner,
        )
        homeRecyclerView.apply {
            adapter = followsAdapter
            (itemAnimator as DefaultItemAnimator).run {
                supportsChangeAnimations = false
                addDuration = 160L
                moveDuration = 160L
                changeDuration = 160L
                removeDuration = 120L
            }
        }
        binding.swipeRefreshLayout.doOnNextLayout {
            setContentMaxWidth(it)
        }

        launchAndRepeatWithViewLifecycle {
            launch {
                followsViewModel.get()
            }

            launch {
                followsViewModel.followsUiData.collect { updateHomeUi(it.data.list) }
            }

            launch {
                followsViewModel.errorMessage.collect { errorMsg ->
             //       Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun updateHomeUi(followsUiData: List<Follows.Data.User>?) {
        // Require everything to be loaded.
        val list = followsUiData?: return

        followsAdapter.submitList(list)
        homeRecyclerView.run {
            if (itemDecorationCount > 0) {
                for (i in itemDecorationCount - 1 downTo 0) {
                    removeItemDecorationAt(i)
                }
            }
        }

        binding.executeAfter {
            isEmpty = list.isEmpty()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}