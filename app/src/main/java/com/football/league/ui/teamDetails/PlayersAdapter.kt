package com.football.league.ui.teamDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.football.commonModels.model.AppPlayer
import com.football.league.databinding.LayoutPlayerItemBinding

class PlayersAdapter :
    ListAdapter<AppPlayer, PlayersAdapter.PlayerViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            LayoutPlayerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = getItem(position)

        holder.item.playerNameTxt.text = player.name
        holder.item.playerPositionTxt.text = player.position
        holder.item.playerNationalityTxt.text = player.nationality

    }

    class PlayerViewHolder(val item: LayoutPlayerItemBinding) :
        RecyclerView.ViewHolder(item.root)
}

class DiffCallback : DiffUtil.ItemCallback<AppPlayer>() {
    override fun areItemsTheSame(oldItem: AppPlayer, newItem: AppPlayer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AppPlayer, newItem: AppPlayer): Boolean {
        return oldItem == newItem
    }
}