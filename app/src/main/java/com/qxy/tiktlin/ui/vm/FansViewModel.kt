package com.qxy.tiktlin.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qxy.tiktlin.common.rsp.Extra
import com.qxy.tiktlin.common.rsp.Fans
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FansViewModel: ViewModel()  {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isEmpty = MutableStateFlow(false)
    val isEmpty: StateFlow<Boolean> = _isEmpty

    private val _fansUiData = MutableStateFlow(Fans(Fans.Data(emptyList()), Extra()))
    val fansUiData: StateFlow<Fans> =_fansUiData


    /**
     * 下拉刷新部分，绑定了view中的下拉刷新部件
     */
    private val _swipeRefreshing = MutableStateFlow(false)
    val swipeRefreshing: StateFlow<Boolean> = _swipeRefreshing

    fun onSwipeRefresh() {
        viewModelScope.launch {
            _swipeRefreshing.emit(true)
            _isLoading.emit(false)
            //代表首页的一个下拉刷新操作
            delay(500)
            _isLoading.emit(true)
            _swipeRefreshing.emit(false)
        }
    }
}