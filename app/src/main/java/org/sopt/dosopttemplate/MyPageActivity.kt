package org.sopt.dosopttemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityMyPageBinding

class MyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val birth = intent.getStringExtra("birth")
        val id = intent.getStringExtra("idresult")

        binding.tvMypageIdName.text = name
        binding.tvMypageIdUserIdContent.hint = id
        binding.tvMypageIdUserBirthContent.hint = birth
    }
}