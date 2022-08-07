package com.qxy.common.model

import kotlinx.serialization.Serializable

@Serializable
data class AccessToken (
    val access_token: String?,
    val log_id: String?,
    val open_id: String?,
)