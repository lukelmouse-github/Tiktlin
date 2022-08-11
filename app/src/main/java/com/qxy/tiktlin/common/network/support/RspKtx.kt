package com.qxy.tiktlin.common.network.support

import com.qxy.common.network.model.DataResult
import retrofit2.Call
import retrofit2.await
import java.lang.RuntimeException

/*
    扩展retrofit的返回数据，
 */
suspend fun <T : Any> Call<T>.serverData(): DataResult<T> {
    var result: DataResult<T> = DataResult.Loading
    kotlin.runCatching {
        this.await()
    }.onFailure {
        result = DataResult.Error(RuntimeException(it))
        it.printStackTrace()
    }.onSuccess {
        result = DataResult.Success(it)
    }
    return result
}

