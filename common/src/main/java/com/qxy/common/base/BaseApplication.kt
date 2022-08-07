package com.qxy.common.base

import android.app.Application

abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Todo 依赖注入 startKoin


        initConfig()

        initData()

    }

    protected open fun initConfig() {

    }

    protected open fun initData() {

    }
}