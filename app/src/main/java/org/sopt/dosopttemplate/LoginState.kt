package org.sopt.dosopttemplate

import org.sopt.dosopttemplate.data.ResponseLoginDto

sealed class LoginState{
    object Loading : LoginState()
    data class Success(val data: ResponseLoginDto) : LoginState()
    object Error : LoginState()
}
