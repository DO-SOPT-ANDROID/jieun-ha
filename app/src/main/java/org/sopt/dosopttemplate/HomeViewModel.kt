package org.sopt.dosopttemplate

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    // 가짜 데이터(친구목록)
    val mockFriendList = listOf<Friend>(
        Friend(
            profileImage = R.drawable.girum,
            name = "기름이",
            description = "나는 털에 윤기가 좔좔 기름이에요",
        ),
        Friend(
            profileImage = R.drawable.baby_girum,
            name = "아기기름",
            description = "망망!",
        ),
        Friend(
            profileImage = R.drawable.ice_girum,
            name = "아이스기름",
            description = "빵빠레 왕!",
        ),
        Friend(
            profileImage = R.drawable.oil_girum,
            name = "촤르르기름",
            description = "멍멍! 다 큰 기름이에욥"
        ),
        Friend(
            profileImage = R.drawable.play_girum,
            name = "기르미",
            description = "둥가둥가",
        ),
        Friend(
            profileImage = R.drawable.professor_girum,
            name = "교수기름",
            description = "자네 중간고사 공부는 했나?",
        ),
        Friend(
            profileImage = R.drawable.sleepy_girum,
            name = "졸려기름",
            description = "졸려서 못해써요.."
        ),
        Friend(
            profileImage = R.drawable.merong_girum,
            name = "메롱기름",
            description = "메렁"
        ),
        Friend(
            profileImage = R.drawable.newborn_girum,
            name = "갓기름",
            description = "지금 태어남"
        ),
        Friend(
            profileImage = R.drawable.bad_girum,
            name = "심기름",
            description = "심기불편"
        )
    )
    // 가짜 데이터(나)
    val mockMyList = listOf<Friend>(
        Friend(
            profileImage = R.drawable.my_dog,
            name = "꼬식이",
            description = "내가 이 구역의 대표 강아지",
        ),
    )
}