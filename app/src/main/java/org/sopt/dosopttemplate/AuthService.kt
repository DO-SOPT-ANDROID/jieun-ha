package org.sopt.dosopttemplate

import org.sopt.dosopttemplate.data.RequestLoginDto
import org.sopt.dosopttemplate.data.RequestMemberCheckDto
import org.sopt.dosopttemplate.data.RequestMemberDto
import org.sopt.dosopttemplate.data.RequestSignUpDto
import org.sopt.dosopttemplate.data.ResponseLoginDto
import org.sopt.dosopttemplate.data.ResponseMemberCheckDto
import org.sopt.dosopttemplate.data.ResponseMemberDto
import org.sopt.dosopttemplate.data.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST("api/v1/members")
    suspend fun signup(
        @Body request: RequestSignUpDto,
    ):Response<ResponseSignUpDto> // 응답이 왓을 때 Callback으로 불려질 타입

    @POST("api/v1/members/sign-in")
    suspend fun login(
        @Body request: RequestLoginDto,
    ): Response<ResponseLoginDto>

    @GET("api/v1/members/{memberId}")
    fun memberInfo(
        @Body request: RequestMemberDto,
    ):Call<ResponseMemberDto>

    @GET("api/v1/members/check")
    fun checkMember(
        @Query("username") username: String,
    ):Call<ResponseMemberCheckDto>
}

