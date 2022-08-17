package com.qxy.tiktlin.model.datasource.database

import com.qxy.tiktlin.data.config.AppConfig
import com.qxy.tiktlin.model.datasource.database.TikDatabase
import com.qxy.tiktlin.model.datasource.database.User

/**
调用示例
viewModelScope.launch {
try {
val FansResult = NetReasouse.getFans()
_status.value = FansResult
} catch (e: Exception) {
_status.value = "Failure: ${e.message}"
}
}
 */

object LocalDataSource {
    private val db = TikDatabase.appDatabase
    suspend fun getUser(open_id: String = AppConfig.OPEN_ID): User {
        return TikDatabase.appDatabase.userDao().getUser(open_id)
    }
}