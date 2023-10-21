package org.sopt.dosopttemplate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.databinding.ActivityMyPageBinding
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding

class MyPageFragment: Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding: FragmentMyPageBinding
        get() = requireNotNull(_binding) { "바인딩 객체가 생성되지 않았다. 생성하고 불러라 임마!" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // 이제 반환하는 View가 Null일 수 없으므로 ? 지워도 됨
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)

        //SignUpActivity에서 MyPageFragment(현재 프래그먼트)로 데이터 전달
        binding.tvMypageIdName.text = arguments?.getString("name")
        binding.tvMypageIdUserIdContent.hint = arguments?.getString("idresult")
        binding.tvMypageIdUserMbtiContent.hint = arguments?.getString("mbti")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}