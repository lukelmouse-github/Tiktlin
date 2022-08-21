package com.qxy.tiktlin.ui.vm

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.qxy.tiktlin.widget.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MeViewModel : BaseViewModel() {}


