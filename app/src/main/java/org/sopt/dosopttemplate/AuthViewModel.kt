package org.sopt.dosopttemplate

import android.provider.ContactsContract.CommonDataKinds.Nickname
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.ServicePool.authService
import org.sopt.dosopttemplate.data.RequestLoginDto
import org.sopt.dosopttemplate.data.RequestSignUpDto

class AuthViewModel : ViewModel() {

    // UI State 사용
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Loading)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Loading)
    val signUpState: StateFlow<SignUpState> = _signUpState.asStateFlow()

    fun login(id: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                val response = authService.login(RequestLoginDto(id, password))
                response.body()
            }.onSuccess {
                if(it != null){
                    _loginState.value = LoginState.Success(it)
                } else{
                    _loginState.value = LoginState.Error
                }
            }.onFailure {
                _loginState.value = LoginState.Error
            }
        }
    }

    fun signUp(id: String, password: String, nickname: String){
        viewModelScope.launch {
            kotlin.runCatching {
                val response = authService.signup(RequestSignUpDto(id, password, nickname))
                response.body()
            }.onSuccess {
                if(it!=null) {
                    _signUpState.value = SignUpState.Success(it)
                }else{
                    _signUpState. value = SignUpState.Error
                }
            }.onFailure {
                _signUpState.value = SignUpState.Error
            }
        }
    }
}