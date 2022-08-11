package com.qxy.tiktlin.common.network.support

interface IHttpCallback {

    fun onSuccess(data: Any?)

    fun onFailed(error: Any?)
}