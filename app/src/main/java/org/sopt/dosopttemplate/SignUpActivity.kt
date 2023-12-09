package org.sopt.dosopttemplate

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.Utils.showToast
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import java.util.Calendar
import java.util.Date
import kotlin.properties.Delegates

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    private val authViewModel by viewModels<AuthViewModel>()
    private var available by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        birth()
        signup()
        observerAvailable()
        observerSignUpResult()
    }

    // 생일 정보 입력
    private fun birth() {
        binding.btnSignUpBirth.setOnClickListener {
            // 생일 선택
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("생일을 선택해주세요.")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()

            datePicker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance()
                // 선택한 날짜를 Date format으로 가져오기
                calendar.time = Date(it)
                // 버튼text를 선택한 날짜로 바꿔주기
                binding.btnSignUpBirth.text =
                    "${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.DAY_OF_MONTH)}/${
                        calendar.get(Calendar.YEAR)
                    }"
            }
            datePicker.show(supportFragmentManager, datePicker.toString()) //datePicker를 보여주기
        }
    }

    // 조건 검사
    private fun testInfo(): Boolean {
        val id = binding.etLoginIdIdHint.text.toString()
        val pw = binding.etLoginIdPwHint.text.toString()
        val nameText = binding.etSignUpIdNametext.text.toString()
        val birth = binding.btnSignUpBirth.text.toString()

        // ID 조건 (영문, 숫자 포함 6~10글자 이내)
        val idRegex = Regex("^[a-zA-Z0-9]{6,10}\$")
        // PW 조건 (영문, 숫자, 특수문자가 포함 6~12글자 이내)
        val pwRegex =
            Regex("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#\$%^&*()-+])[a-zA-Z\\d!@#\$%^&*()-+]{6,12}\$")

        if (nameText.isEmpty() || id.isEmpty() ||
            pw.isEmpty() || birth.isEmpty() ||
            !id.matches(idRegex) ||
            !pw.matches(pwRegex) ||
            nameText.length < 2
        ) {
            Snackbar.make(
                binding.root,
                "모든 정보를 올바르게 입력해주세요",
                Snackbar.LENGTH_SHORT
            ).show()
            return false
        } else {
            return true
        }
    }

    private fun observerSignUpResult() {
        authViewModel.signUpSuccess.observe(this) {
            if (it) {
                showToast("회원가입 성공")
                startActivity(
                    Intent(
                        this,
                        LoginActivity::class.java
                    )
                )
            } else {
                showToast("회원가입 실패")
            }
        }
    }

    // 아이디 중복 확인
    private fun isExist() {
        var username = binding.etLoginIdIdHint.text.toString() // 아이디로 검색
        authViewModel.checkID(id = username)
    }

    private fun observerAvailable() {
        authViewModel.checkAvailable.observe(this) { isAvailable ->
            available = isAvailable
            if (isAvailable) {
                showToast("사용 가능한 아이디입니다.")
            } else {
                showToast("이미 사용 중인 아이디입니다.")
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun changeButton(): Boolean {
        val testResult = testInfo()
        if (testResult) {
            binding.btnLoginIdSignUp.isEnabled = true
            binding.btnLoginIdSignUp.setBackgroundColor(R.color.light_green)
        }
        return testResult
    }

    // 회원가입 버튼 클릭 시
    private fun signup() {
        binding.btnLoginIdSignUp.setOnClickListener {
            val testResult = changeButton()

            val id = binding.etLoginIdIdHint.text.toString()
            val pw = binding.etLoginIdPwHint.text.toString()
            val nickname = binding.etSignUpIdNametext.text.toString()

            isExist()
            if (available) {
                showToast("중복된 아이디입니다.")
            } else if (testResult && !available) { // 조건이 적절하고 중복된 아이디가 아닌 경우
                authViewModel.signUp(id = id, pw = pw, name = nickname)
            } else {
                showToast("올바른 정보를 입력해주세요.")
            }
        }
    }
}
