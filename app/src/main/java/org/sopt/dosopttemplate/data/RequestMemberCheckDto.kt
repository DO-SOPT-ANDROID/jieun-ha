package org.sopt.dosopttemplate.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestMemberCheckDto(
    @SerialName("username")
    val username: String
)
