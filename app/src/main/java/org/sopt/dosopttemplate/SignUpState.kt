package org.sopt.dosopttemplate

import org.sopt.dosopttemplate.data.ResponseSignUpDto

sealed class SignUpState {
    object Loading : SignUpState()
    data class Success(val data: ResponseSignUpDto) : SignUpState()
    object Error : SignUpState()
}
