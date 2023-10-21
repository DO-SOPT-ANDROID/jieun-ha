package org.sopt.dosopttemplate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosopttemplate.Utils.UserInfo
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

        binding.tvMypageIdName.text = UserInfo.userName
        binding.tvMypageIdUserIdContent.hint = UserInfo.userID
        binding.tvMypageIdUserMbtiContent.hint = UserInfo.userMbti

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