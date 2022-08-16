package com.qxy.tiktlin.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.qxy.tiktlin.widget.appInstance
import com.qxy.tiktlin.db.dao.UserDao
import com.qxy.tiktlin.db.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
public abstract class TikDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        val appDatabase by lazy {
            Room.databaseBuilder(appInstance.applicationContext,
                TikDatabase::class.java,
                "TikDatabase").build()
        }
//        fun getDatabase(): TikDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    appInstance,
//                    TikDatabase::class.java,
//                    "TikDatabase"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }

    }
}