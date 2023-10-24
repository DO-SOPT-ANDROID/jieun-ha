package org.sopt.dosopttemplate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemMineBinding


class MyAdapter(context: Context) : RecyclerView.Adapter<MyViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }

    // 임시 빈 리스트
    private var friendList: List<Friend> = emptyList()

    // 어댑터 클래스 필수 함수 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemMineBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    // 어댑터 클래스 필수 함수 2
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(friendList[position])
    }

    // 어댑터 클래스 필수 함수 3
    override fun getItemCount() = friendList.size

    // 임시 리스트에 가짜 리스트를 연결
    fun setFriendList(friendList: List<Friend>) {
        this.friendList = friendList.toList()
        notifyDataSetChanged() // 어댑터에 데이터의 변화 알림
    }
}