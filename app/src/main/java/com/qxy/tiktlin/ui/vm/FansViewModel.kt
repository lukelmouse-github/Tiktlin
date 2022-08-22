package com.qxy.tiktlin.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qxy.tiktlin.data.netData.Extra
import com.qxy.tiktlin.data.netData.Fans
import com.qxy.tiktlin.model.repository.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FansViewModel: ViewModel()  {


    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isEmpty = MutableStateFlow(false)
    val isEmpty: StateFlow<Boolean> = _isEmpty

    private val _fansUiData = MutableStateFlow(Fans(Fans.Data(emptyList()), Extra()))
    val fansUiData: StateFlow<Fans> =_fansUiData

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> =_errorMessage

    private var course :Long=-1

    suspend fun get(){
        _isLoading.emit(true)
        course++
        _fansUiData.emit(Repository.getFans(course,20))
        _isEmpty.emit(false)
        _isLoading.emit(false)
    }


    /**
     * 下拉刷新部分，绑定了view中的下拉刷新部件
     */
    private val _swipeRefreshing = MutableStateFlow(false)
    val swipeRefreshing: StateFlow<Boolean> = _swipeRefreshing

    fun onSwipeRefresh() {
        viewModelScope.launch {
            _isLoading.emit(true)
            _swipeRefreshing.emit(true)
            delay(400)
            course=-1
            _swipeRefreshing.emit(false)
            _isLoading.emit(false)
        }
    }
}