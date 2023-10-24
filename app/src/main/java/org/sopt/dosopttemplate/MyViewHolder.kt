package org.sopt.dosopttemplate

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemMineBinding

class MyViewHolder(private val binding: ItemMineBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: Friend) {
        binding.ivItemMineProfile.setImageResource(friendData.profileImage)
        binding.tvItemMineName.text = friendData.name
        binding.tvItemMineDescription.text = friendData.description
    }
}