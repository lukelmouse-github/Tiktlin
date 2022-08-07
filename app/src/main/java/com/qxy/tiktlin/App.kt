package com.qxy.tiktlin

import com.qxy.common.base.BaseApplication
import com.tencent.mmkv.MMKV
import org.koin.core.context.startKoin
import timber.log.Timber

class App : BaseApplication() {
    override fun initConfig() {
        super.initConfig()

        MMKV.initialize(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}