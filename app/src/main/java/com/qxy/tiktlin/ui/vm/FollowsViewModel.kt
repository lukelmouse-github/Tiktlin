package com.qxy.tiktlin.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qxy.tiktlin.data.netData.Extra
import com.qxy.tiktlin.data.netData.Fans
import com.qxy.tiktlin.data.netData.Follows
import com.qxy.tiktlin.model.repository.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FollowsViewModel : ViewModel()  {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isEmpty = MutableStateFlow(false)
    val isEmpty: StateFlow<Boolean> = _isEmpty

    private val _followsUiData = MutableStateFlow(Follows(Follows.Data(emptyList()), Extra()))
    val followsUiData: StateFlow<Follows> = _followsUiData


    private var course :Long=-1

    suspend fun get(){
        course++
        _followsUiData.emit(Repository.getFollows(course,20))
    }

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> =_errorMessage
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