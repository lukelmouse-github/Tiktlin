package com.qxy.common.base

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@BaseApplication)
        }

        initConfig()

        initData()

    }

    protected open fun initConfig() {

    }

    protected open fun initData() {

    }
}