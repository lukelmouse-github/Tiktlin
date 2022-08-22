package com.qxy.tiktlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.doOnNextLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.qxy.tiktlin.R
import com.qxy.tiktlin.data.netData.Fans
import com.qxy.tiktlin.databinding.FragmentMeFansBinding
import com.qxy.tiktlin.home.ui.FansAdapter
import com.qxy.tiktlin.ui.vm.FansViewModel
import com.qxy.tiktlin.util.setContentMaxWidth
import com.qxy.tiktlin.widget.executeAfter
import com.qxy.tiktlin.widget.launchAndRepeatWithViewLifecycle
import kotlinx.coroutines.launch

class FansFragment: Fragment() {
    private var _binding: FragmentMeFansBinding? = null

    private val binding get() = _binding!!

    private val fansViewModel: FansViewModel by viewModels()

    private lateinit var homeRecyclerView: RecyclerView

    private lateinit var fansAdapter: FansAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fansViewModel: FansViewModel by viewModels()

        _binding = FragmentMeFansBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = fansViewModel
        }

        homeRecyclerView = binding.recyclerviewFans

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Session list configuration
        fansAdapter = FansAdapter(
            viewLifecycleOwner,
        )
        homeRecyclerView.apply {
            adapter = fansAdapter
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
                fansViewModel.get()
            }

            launch {
                fansViewModel.fansUiData.collect { updateHomeUi(it.data.list) }
            }

            launch {
                fansViewModel.errorMessage.collect { errorMsg ->
                    Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun updateHomeUi(fansUiData: List<Fans.Data.User>?) {
        // Require everything to be loaded.
        val list = fansUiData?: return

        fansAdapter.submitList(list)
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