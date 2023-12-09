package org.sopt.dosopttemplate.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignUpDto(
    @SerialName("Location")
    val location: String,
)
