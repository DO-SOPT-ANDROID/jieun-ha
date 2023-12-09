package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.sopt.dosopttemplate.Utils.showToast
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.authViewModel = authViewModel

        signup()
        login()
        observerLoginResult()
    }

    // 로그인 버튼 클릭 시
    private fun login() {
        binding.btnLoginIdSignIn.setOnClickListener {
            val id = binding.etLoginIdIdHint.text.toString()
            val pw = binding.etLoginIdPwHint.text.toString()

            authViewModel.login(id = id, password = pw)
        }
    }

    private fun observerLoginResult() {
        authViewModel.loginSuccess.observe(this) {
            if (it) {
                showToast("로그인 성공")
                startActivity(
                    Intent(
                        this,
                        HomeActivity::class.java
                    )
                )
            } else {
                showToast("로그인 실패")
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