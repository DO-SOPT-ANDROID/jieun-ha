package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.ServicePool.authService
import org.sopt.dosopttemplate.Utils.showToast
import org.sopt.dosopttemplate.data.RequestLoginDto
import org.sopt.dosopttemplate.data.ResponseLoginDto
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signup()
        login()
    }

    // 로그인 버튼 클릭 시
    private fun login() {
        val id = binding.etLoginIdIdHint.text.toString()
        val pw = binding.etLoginIdPwHint.text.toString()

        binding.btnLoginIdSignIn.setOnClickListener {
            // Request 요청 : RequestLoginDto body 담아주기
            authService.login(RequestLoginDto(id, pw))
                // enqueue : 비동기 방식으로 네트워크 요청 보내고, 결과를 받아 알맞게 처리
                // Callback 인터페이스를 구현한 객체를 전달
                .enqueue(object : retrofit2.Callback<ResponseLoginDto> {
                    // 요청 수행된 경우 호출
                    override fun onResponse(
                        call: Call<ResponseLoginDto>,
                        response: Response<ResponseLoginDto>
                    ) {
                        if (response.isSuccessful) {
                            val data: ResponseLoginDto = response.body()!!
                            val userId = data.id
                            showToast("로그인이 성공했습니다. 유저 ID는 $userId 입니다.")
                        }

                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                    }

                    // 네트워크 요청 중 오류 발생 시 호출
                    override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                        showToast("서버 에러 발생")
                    }
                }
                )
        }
    }

    // 회원가입 버튼 클릭 시
    private fun signup(){
        // 회원가입 페이지로 이동
        binding.btnLoginIdSignUp.setOnClickListener {
            // Intent 실행
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}