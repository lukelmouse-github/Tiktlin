package com.qxy.common.network.support

import androidx.lifecycle.LiveData
import com.qxy.common.network.model.ApiResponse
import com.qxy.common.network.model.UNKNOW_ERROR_CODE
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

// retrofit call 转化成 livedata 到 adapter
class LiveDataCallAdapter<R>(private val responseType: Type) : CallAdapter<R, LiveData<ApiResponse<R>>>{
    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): LiveData<ApiResponse<R>> {
        return object : LiveData<ApiResponse<R>>() {
            private var stared = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (stared.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(ApiResponse.create(response))
                        }

                        override fun onFailure(call: Call<R>, t: Throwable) {
                            postValue(ApiResponse.create(UNKNOW_ERROR_CODE, t))
                        }
                    })
                }
            }
        }
    }

}