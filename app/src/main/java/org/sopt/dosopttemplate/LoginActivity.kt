package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.Utils.showToast
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signup()
        observeLoginResult()
        login()
    }

    // 로그인 버튼 클릭 시
    private fun login() {

        binding.btnLoginIdSignIn.setOnClickListener {
            val id = binding.etLoginIdIdHint.text.toString()
            val pw = binding.etLoginIdPwHint.text.toString()

            authViewModel.login(
                id = id,
                password = pw
            )

            /*// Request 요청 : RequestLoginDto body 담아주기
            authService.login(RequestLoginDto(id, pw))
                // enqueue : 비동기 방식으로 네트워크 요청 보내고, 결과를 받아 알맞게 처리
                // Callback 인터페이스를 구현한 객체를 전달
                .enqueue(object : retrofit2.Callback<ResponseLoginDto> {
                    // 요청 수행된 경우 호출
                    override fun onResponse(
                        call: Call<ResponseLoginDto>,
                        response: Response<ResponseLoginDto>
                    ) {
                        when(response.code()){
                            200 -> {
                                // 로그인 성공
                                val data: ResponseLoginDto = response.body()!!
                                val userId = data.id
                                showToast("로그인이 성공했습니다. 유저 ID는 $userId 입니다.")

                                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                                startActivity(intent)
                            }
                            400 -> {
                                // 로그인 실패
                                showToast("로그인에 실패했습니다.")
                            }
                            else -> {
                                showToast("서버 에러 발생")
                            }
                        }
                    }

                    // 네트워크 요청 중 오류 발생 시 호출
                    override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                        showToast("네트워크 오류 발생")
                    }
                }
                )*/
        }
    }

    private fun observeLoginResult() {
        lifecycleScope.launch {
            authViewModel.loginState.collect{ loginState ->
                when(loginState){
                    is LoginState.Success -> {
                        showToast("로그인 성공")
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    }
                    is LoginState.Error -> {
                        showToast("로그인 실패")
                    }
                    is LoginState.Loading -> {
                        showToast("로그인 중")
                    }
                }
            }
        }
    }

    // 회원가입 버튼 클릭 시
    private fun signup() {
        // 회원가입 페이지로 이동
        binding.btnLoginIdSignUp.setOnClickListener {
            // Intent 실행
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}