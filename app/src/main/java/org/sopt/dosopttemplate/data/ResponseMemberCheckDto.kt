package org.sopt.dosopttemplate.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMemberCheckDto(
    @SerialName ("isExist")
    val isExist: Boolean,
)
