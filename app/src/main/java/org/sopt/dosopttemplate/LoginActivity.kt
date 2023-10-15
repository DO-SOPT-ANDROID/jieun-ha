package org.sopt.dosopttemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.snackbar.Snackbar
import android.content.Intent
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var getResultID: ActivityResultLauncher<Intent> // 반환
    var getid: String = "000000"
    var getpw: String = "000000"
    var getname: String = "이름"
    var getmbti: String = "EEEE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ActivityResultLauncher 초기화, 결과값 이벤트 핸들러 정의
        getResultID =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    getid = result.data?.getStringExtra("idresult").toString()
                    getpw = result.data?.getStringExtra("pwresult").toString()
                    getname = result.data?.getStringExtra("name").toString()
                    getmbti = result.data?.getStringExtra("mbti").toString()
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

            if (id == getid && pw == getpw) {
                toast("로그인 성공!")
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("name", getname)
                intent.putExtra("mbti", getmbti)
                intent.putExtra("idresult", getid)
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