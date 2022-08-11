package com.qxy.tiktlin.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo(name = "nickname")
    val name: String,
    @ColumnInfo(name = "open_id")
    val openId: String,
    val avatar: String,
    val gender: Long,
    val country: String,
    val province: String,
    val city: String,
) {
}

//val avatar: String? = null,
//val country: String? = null,
//val gender: Long? = null,
//val nickname: String? = null,
//val open_id: String? = null,
//val province: String? = null,
//val city: String? = null,
//val description: String? = null,
//val e_account_role: String? = null,
//val error_code: Long? = null,
//val union_id: String? = null