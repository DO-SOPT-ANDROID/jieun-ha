package org.sopt.dosopttemplate

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.internal.ContextUtils
import org.sopt.dosopttemplate.ServicePool.authService
import org.sopt.dosopttemplate.Utils.showToast
import org.sopt.dosopttemplate.data.RequestLoginDto
import org.sopt.dosopttemplate.data.RequestSignUpDto
import org.sopt.dosopttemplate.data.ResponseLoginDto
import org.sopt.dosopttemplate.data.ResponseSignUpDto
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

    private val _signUpResult: MutableLiveData<ResponseSignUpDto> = MutableLiveData()
    val signUpResult: LiveData<ResponseSignUpDto> get() = _signUpResult

    private val _signUpSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val signUpSuccess: LiveData<Boolean> get() = _signUpSuccess

    fun signUp(id: String, pw: String, name: String){
        authService.signup(RequestSignUpDto(id, pw, name))
            .enqueue(object: Callback<ResponseSignUpDto>{
                override fun onResponse(
                    call: Call<ResponseSignUpDto>,
                    response: Response<ResponseSignUpDto>
                ) {
                    if (response.isSuccessful) {
                        when(response.code()){
                            201 -> {
                                // 회원가입 성공
                                _signUpResult.value = response.body()
                                _signUpSuccess.value = true
                            }

                            400 -> {
                                // 회원가입 실패
                                _signUpSuccess.value = false
                            }

                            else -> {
                                _signUpSuccess.value = false
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                }
            })
    }
}