package com.qxy.tiktlin.model

import com.drake.serialize.serialize.deserialize
import com.qxy.common.base.BaseViewModel
import com.qxy.tiktlin.Repository

class MainViewModel : BaseViewModel() {
    fun getAccessToken() = serverAwait {
        val accessToken = Repository.getAccessToken(authCode = deserialize("authCode"))
    }
}