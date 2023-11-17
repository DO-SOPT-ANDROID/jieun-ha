package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.Utils.UserInfo
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Calendar
import java.util.Date

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 생일 선택
        binding.btnSignUpBirth.setOnClickListener {
            // 생일 선택
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("생일을 선택해주세요.")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()

            datePicker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance()
                //선택한 날짜를 Date format으로 가져오기
                calendar.time = Date(it)
                //버튼text를에 선택한 날짜로바꿔주기
                binding.btnSignUpBirth.text = "${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.YEAR)}"
            }
            datePicker.show(supportFragmentManager,datePicker.toString()) //datePicker를 보여주기
        }

        // 회원가입 버튼 클릭 시
        binding.btnLoginIdSignUp.setOnClickListener {
            val testResult = testInfo() // 조건 검사 결과를 저장

            if (testResult) { // 조건이 적절한 경우
                val intent = Intent(this, LoginActivity::class.java)

                // UserInfo의 companion object에 저장
                UserInfo.userName = binding.etSignUpIdNametext.text.toString()
                UserInfo.userID = binding.etLoginIdIdHint.text.toString()
                UserInfo.userPW = binding.etLoginIdPwHint.text.toString()
                UserInfo.userBirth = binding.btnSignUpBirth.text.toString()

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
        val birth = binding.btnSignUpBirth.text.toString()

        if (nameText.isEmpty() || id.isEmpty() ||
            pw.isEmpty() || birth.isEmpty() ||
            id.length !in 6..10 ||
            pw.length !in 8..12 ||
            nameText.length < 2
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
