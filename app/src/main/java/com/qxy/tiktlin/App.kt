package com.qxy.tiktlin

import android.app.Application
import com.drake.logcat.LogCat
import com.google.android.material.color.DynamicColors
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

lateinit var appInstance: App

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this

        // Koin
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
        }
        if (BuildConfig.DEBUG) {
            // 日志库
            LogCat.setDebug(BuildConfig.DEBUG)
        }

        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}
