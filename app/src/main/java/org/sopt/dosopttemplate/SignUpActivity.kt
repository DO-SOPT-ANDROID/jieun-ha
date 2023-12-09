package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.ServicePool.authService
import org.sopt.dosopttemplate.Utils.showToast
import org.sopt.dosopttemplate.data.ResponseMemberCheckDto
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Response
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

    private fun observerSignUpResult(){
        authViewModel.signUpSuccess.observe(this){
            if(it){
                showToast("회원가입 성공")
                startActivity(
                    Intent(
                        this,
                        LoginActivity::class.java
                    )
                )
            } else{
                showToast("회원가입 실패")
            }
        }
    }

    // 아이디 중복 확인
    private fun isExist() {
        var username = binding.etLoginIdIdHint.text.toString() // 아이디로 검색
        authViewModel.checkID(id = username)
    }

    // 회원가입 버튼 클릭 시
    private fun signup() {
        binding.btnLoginIdSignUp.setOnClickListener {
            val testResult = testInfo() // 조건 검사 결과를 저장

            val id = binding.etLoginIdPwHint.text.toString()
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
