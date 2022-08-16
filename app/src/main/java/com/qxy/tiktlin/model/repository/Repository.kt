package com.qxy.tiktlin.model.repository

import com.qxy.tiktlin.model.datasource.database.LocalDataSource
import com.qxy.tiktlin.model.datasource.network.NetDataSource

object Repository {
    private val localDataSource= LocalDataSource
    private val netDataSource= NetDataSource
}