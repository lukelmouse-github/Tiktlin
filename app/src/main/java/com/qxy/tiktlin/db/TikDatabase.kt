package com.qxy.tiktlin.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.qxy.tiktlin.appInstance
import com.qxy.tiktlin.db.dao.UserDao

@Database(entities = [UserDao::class], version = 1, exportSchema = false)
public abstract class TikDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        val TikDatabase by lazy {
            Room.databaseBuilder(appInstance.applicationContext,
                TikDatabase::class.java,
                "TikDatabase").build()
        }
    }
}