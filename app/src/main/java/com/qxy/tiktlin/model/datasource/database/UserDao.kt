package com.qxy.tiktlin.model.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.qxy.tiktlin.model.datasource.database.User

// https://developer.android.com/codelabs/android-room-with-a-view-kotlin?hl=zh-cn#4
@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE open_id = :open_id")
    fun getUser(open_id: String): User

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(user: User)
}