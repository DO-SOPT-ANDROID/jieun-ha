package org.sopt.dosopttemplate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.ServicePool.authService
import org.sopt.dosopttemplate.data.RequestLoginDto
import org.sopt.dosopttemplate.data.ResponseLoginDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel : ViewModel() {
    // Backing Property 적용
    private val _loginResult: MutableLiveData<ResponseLoginDto> = MutableLiveData()
    val loginResult: LiveData<ResponseLoginDto> get() = _loginResult

    private val _loginSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    fun login(id: String, password: String) {
        authService.login(RequestLoginDto(id, password))
            .enqueue(object : Callback<ResponseLoginDto> {
                override fun onResponse(
                    call: Call<ResponseLoginDto>,
                    response: Response<ResponseLoginDto>,
                ) {
                    if (response.isSuccessful) {
                        _loginResult.value = response.body()
                        _loginSuccess.value = true
                    } else {
                        _loginSuccess.value = false
                    }
                }

                override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                }
            })
    }

    val isLoginButtonClicked: MutableLiveData<Boolean> = MutableLiveData(false)
    fun onLoginButtonClick() {
        isLoginButtonClicked.value = true
    }
}