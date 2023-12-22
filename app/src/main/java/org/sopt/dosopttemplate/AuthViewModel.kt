package org.sopt.dosopttemplate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.ServicePool.authService
import org.sopt.dosopttemplate.data.RequestLoginDto

class AuthViewModel : ViewModel() {

    // UI State 사용
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Loading)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

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
}