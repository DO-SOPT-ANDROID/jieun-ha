package org.sopt.dosopttemplate

import androidx.annotation.DrawableRes

data class Friend(
    @DrawableRes val profileImage: Int, // Int 값일 시 컴파일 에러
    val name: String,
    val description: String,
)
