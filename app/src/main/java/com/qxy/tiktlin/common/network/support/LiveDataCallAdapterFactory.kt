package com.qxy.tiktlin.common.network.support

import com.qxy.common.network.support.LiveDataCallAdapter
import retrofit2.CallAdapter
import retrofit2.CallAdapter.Factory
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

// adapter 的 工厂类
// https://mobikul.com/how-to-use-livedata-with-retrofit/
sealed class LiveDataCallAdapterFactory : Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType) as? ParameterizedType
            ?: throw IllegalArgumentException("resource must be parameterized")
        return LiveDataCallAdapter<Any>(getParameterUpperBound(0, observableType))
    }
}