package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 회원가입 버튼 클릭 시
        binding.btnSignUp.setOnClickListener {
            val testResult = testInfo() // 조건 검사 결과를 저장

            if (testResult) { // 조건이 적절한 경우
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("idresult", binding.editText.text.toString())
                intent.putExtra("pwresult", binding.editText2.text.toString())
                intent.putExtra("mbti", binding.MbtiText.text.toString())
                intent.putExtra("name", binding.nameText.text.toString())
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
        val nameText = binding.nameText.text.toString()
        val id = binding.editText.text.toString()
        val pw = binding.editText2.text.toString()
        val MbtiText = binding.MbtiText.text.toString()

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
