package org.sopt.dosopttemplate.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestMemberDto(
    @SerialName ("memberId")
    val memberId: Int,
)
