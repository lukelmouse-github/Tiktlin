package com.qxy.tiktlin

import android.app.Application
import com.google.android.material.color.DynamicColors
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

lateinit var appInstance: App

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}
