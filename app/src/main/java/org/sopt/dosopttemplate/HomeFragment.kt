package org.sopt.dosopttemplate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩 객체가 생성되지 않았다. 생성하고 불러라 임마!" }

    // 가짜 데이터
    private val mockFriendList = listOf<Friend>(
        Friend(
            profileImage = R.drawable.girum,
            name="기름이",
            description = "나는 털에 윤기가 좔좔 기름이에요",
        ),
        Friend(
            profileImage = R.drawable.baby_girum,
            name="아기기름",
            description = "망망!",
        ),
        Friend(
            profileImage = R.drawable.ice_girum,
            name="아이스기름",
            description = "빵빠레 왕!",
        ),
        Friend(
            profileImage = R.drawable.oil_girum,
            name="촤르르기름",
            description = "멍멍! 다 큰 기름이에욥"
        ),
        Friend(
            profileImage = R.drawable.play_girum,
            name="기르미",
            description = "둥가둥가",
        ),
        Friend(
            profileImage = R.drawable.professor_girum,
            name="교수기름",
            description = "자네 중간고사 공부는 했나?",
        ),
        Friend(
            profileImage = R.drawable.sleepy_girum,
            name="졸려기름",
            description = "졸려서 못해써요.."
        )
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // 이제 반환하는 View가 Null일 수 없으므로 ? 지워도 됨
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val friendAdapter = FriendAdapter(requireContext())
        binding.rvFriends.adapter = friendAdapter
        friendAdapter.setFriendList(mockFriendList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}