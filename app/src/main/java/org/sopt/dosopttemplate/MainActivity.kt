package org.sopt.dosopttemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.dosopttemplate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val mbti = intent.getStringExtra("mbti")
        val id = intent.getStringExtra("idresult")

        binding.tvMypageIdName.text = name
        binding.tvMypageIdUserIdContent.hint = id
        binding.tvMypageIdUserMbtiContent.hint = mbti
    }
}