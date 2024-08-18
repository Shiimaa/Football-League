package com.football.league.ui.competitionDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.football.commonModels.model.AppCompetitionTeam
import com.football.league.R
import com.football.league.databinding.LayoutTeamItemBinding

class TeamsAdapter :
    ListAdapter<AppCompetitionTeam, TeamsAdapter.TeamsViewHolder>(DiffCallback()) {
    private var onItemClicked: OnItemClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        return TeamsViewHolder(
            LayoutTeamItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        val team = getItem(position)

        holder.item.teamNameTxt.text = team.name
        holder.item.teamShortNameTxt.text = team.shortName

        Glide.with(holder.item.teamIv)
            .load(team.logo)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(holder.item.teamIv)

        holder.itemView.setOnClickListener {
            onItemClicked?.onClick(team.id)
        }
    }

    fun setOnItemClick(onItemClicked: OnItemClicked) {
        this.onItemClicked = onItemClicked
    }

    class TeamsViewHolder(val item: LayoutTeamItemBinding) :
        RecyclerView.ViewHolder(item.root)

    interface OnItemClicked {
        fun onClick(teamId: Long)

    }

}

class DiffCallback : DiffUtil.ItemCallback<AppCompetitionTeam>() {
    override fun areItemsTheSame(oldItem: AppCompetitionTeam, newItem: AppCompetitionTeam): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AppCompetitionTeam, newItem: AppCompetitionTeam): Boolean {
        return oldItem == newItem
    }
}