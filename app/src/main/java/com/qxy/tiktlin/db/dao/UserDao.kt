package com.qxy.tiktlin.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.qxy.tiktlin.db.entity.User

// https://developer.android.com/codelabs/android-room-with-a-view-kotlin?hl=zh-cn#4
@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE id = :open_id")
    fun get(open_id: Long): User

    @Insert
    fun insert(user: User): Long

    @Update
    fun updateUser(user: User)
}