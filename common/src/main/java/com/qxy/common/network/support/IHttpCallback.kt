package com.qxy.common.network.support

interface IHttpCallback {

    fun onSuccess(data: Any?)

    fun onFailed(error: Any?)
}