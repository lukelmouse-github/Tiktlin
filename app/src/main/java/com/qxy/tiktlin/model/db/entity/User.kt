package com.qxy.tiktlin.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "nickname")
    val name: String,
    @ColumnInfo(name = "open_id")
    val openId: String,
    val avatar: String,
    val gender: Long,
    val country: String,
    val province: String,
    val city: String,
    val totalFans: Int,
): Serializable