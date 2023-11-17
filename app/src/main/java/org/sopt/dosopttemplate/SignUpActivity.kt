package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.ServicePool.authService
import org.sopt.dosopttemplate.Utils.showToast
import org.sopt.dosopttemplate.data.RequestSignUpDto
import org.sopt.dosopttemplate.data.ResponseMemberCheckDto
import org.sopt.dosopttemplate.data.ResponseSignUpDto
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Response
import java.util.Calendar
import java.util.Date
import java.util.concurrent.CountDownLatch
import kotlin.properties.Delegates

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    private var available by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        birth()
        signup()
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

    // 아이디 중복 확인
    private fun isExist() {
        var username = binding.etLoginIdIdHint.text.toString() // 아이디로 검색

        authService.checkMember(username)
            .enqueue(object : retrofit2.Callback<ResponseMemberCheckDto> {
                override fun onResponse(
                    call: Call<ResponseMemberCheckDto>,
                    response: Response<ResponseMemberCheckDto>
                ) {
                    if (response.isSuccessful) {
                        val data: ResponseMemberCheckDto = response.body()!!
                        available = data.isExist
                    }
                }

                override fun onFailure(call: Call<ResponseMemberCheckDto>, t: Throwable) {
                    showToast("서버 에러 발생")
                }
            })
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
                authService.signup(RequestSignUpDto(id, pw, nickname))
                    .enqueue(object : retrofit2.Callback<ResponseSignUpDto> {
                        override fun onResponse(
                            call: Call<ResponseSignUpDto>,
                            response: Response<ResponseSignUpDto>
                        ) {
                            when (response.code()) {
                                201 -> {
                                    // 회원가입 성공
                                    showToast("회원가입 성공!")
                                    val intent =
                                        Intent(this@SignUpActivity, LoginActivity::class.java)
                                    startActivity(intent)
                                }

                                400 -> {
                                    // 회원가입 실패
                                    showToast("회원가입에 실패했습니다.")
                                }

                                else -> {
                                    showToast("서버 에러 발생")
                                }
                            }
                        }

                        override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                            showToast("네트워크 에러 발생")
                        }
                    })
            } else {
                showToast("올바른 정보를 입력해주세요.")
            }
        }
    }
}
