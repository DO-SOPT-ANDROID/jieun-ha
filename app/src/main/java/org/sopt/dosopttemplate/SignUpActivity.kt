package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.Utils.UserInfo
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 회원가입 버튼 클릭 시
        binding.btnLoginIdSignUp.setOnClickListener {
            val testResult = testInfo() // 조건 검사 결과를 저장

            if (testResult) { // 조건이 적절한 경우
                val intent = Intent(this, LoginActivity::class.java)

                // UserInfo의 companion object에 저장
                UserInfo.userName = binding.etSignUpIdNametext.text.toString()
                UserInfo.userID = binding.etLoginIdIdHint.text.toString()
                UserInfo.userPW = binding.etLoginIdPwHint.text.toString()
                UserInfo.userMbti = binding.etSignUpIdMbtitext.text.toString()

                Snackbar.make(
                    binding.root,
                    "회원가입 성공!",
                    Snackbar.LENGTH_SHORT
                ).show()
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    // 조건 검사하는 함수
    private fun testInfo(): Boolean {
        val nameText = binding.etSignUpIdNametext.text.toString()
        val id = binding.etLoginIdPwHint.text.toString()
        val pw = binding.etLoginIdPwHint.text.toString()
        val MbtiText = binding.etSignUpIdMbtitext.text.toString()

        if (nameText.isEmpty() || id.isEmpty() ||
            pw.isEmpty() || MbtiText.isEmpty() ||
            id.length !in 6..10 ||
            pw.length !in 8..12 ||
            nameText.length < 2 ||
            MbtiText.length != 4
        ) {
            Snackbar.make(
                binding.root,
                "모든 정보를 입력해주세요",
                Snackbar.LENGTH_SHORT
            ).show()
            return false
        } else {
            return true
        }
    }
}
