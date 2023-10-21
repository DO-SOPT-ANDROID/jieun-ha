package org.sopt.dosopttemplate

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: Friend) {
        binding.ivItemFriendProfile.setImageResource(friendData.profileImage)
        binding.tvItemFriendName.text = friendData.name
        binding.tvItemFriendDescription.text = friendData.description
    }
}