package org.sopt.dosopttemplate.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMemberDto(
    @SerialName ("username")
    val username: String,
    @SerialName("nickname")
    val nickname: String,
)
