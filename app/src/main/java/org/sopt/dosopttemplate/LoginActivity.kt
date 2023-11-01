package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.Utils.UserInfo
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var getResultID: ActivityResultLauncher<Intent> // 반환

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ActivityResultLauncher 초기화, 결과값 이벤트 핸들러 정의
        getResultID =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                }
            }

        // 회원가입 페이지로 이동
        binding.btnLoginIdSignUp.setOnClickListener {
            // Intent 실행
            val intent = Intent(this, SignUpActivity::class.java)
            getResultID.launch(intent)
        }

        // 로그인 버튼 클릭 시
        binding.btnLoginIdSignIn.setOnClickListener {
            var id: String = binding.etLoginIdIdHint.text.toString()
            var pw: String = binding.etLoginIdPwHint.text.toString()

            if (id == UserInfo.userID && pw == UserInfo.userPW) {
                toast("로그인 성공!")
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
            } else {
                toast("로그인 실패!")
            }
        }
    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}