package com.football.league.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.football.commonModels.model.AppCompetition
import com.football.league.databinding.LayoutCompetitionItemBinding

class CompetitionsAdapter :
    ListAdapter<AppCompetition, CompetitionsAdapter.CompetitionsViewHolder>(DiffCallback()) {
    private var onItemClicked: OnItemClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionsViewHolder {
        return CompetitionsViewHolder(
            LayoutCompetitionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CompetitionsViewHolder, position: Int) {
        val competitionItem = getItem(position)

        holder.item.competitionTxt.text = competitionItem.name

        Glide.with(holder.item.competitionIv)
            .load(competitionItem.logo)
            .into(holder.item.competitionIv)

        holder.itemView.setOnClickListener {
            onItemClicked?.onClick(competitionItem.id)
        }
    }

    fun setOnItemClick(onItemClicked: OnItemClicked) {
        this.onItemClicked = onItemClicked
    }

    class CompetitionsViewHolder(val item: LayoutCompetitionItemBinding) :
        RecyclerView.ViewHolder(item.root)

    interface OnItemClicked {
        fun onClick(competitionId: Long)

    }

}

class DiffCallback : DiffUtil.ItemCallback<AppCompetition>() {
    override fun areItemsTheSame(oldItem: AppCompetition, newItem: AppCompetition): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AppCompetition, newItem: AppCompetition): Boolean {
        return oldItem == newItem
    }
}