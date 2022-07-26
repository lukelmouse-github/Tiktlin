package com.qxy.tiktlin.model.datasource.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.qxy.tiktlin.widget.appInstance

@Database(entities = [User::class], version = 1, exportSchema = false)
public abstract class TikDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        val appDatabase by lazy {
            Room.databaseBuilder(appInstance.applicationContext,
                TikDatabase::class.java,
                "TikDatabase").build()
        }
    }
}