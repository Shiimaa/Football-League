package com.football.league.ui.teamDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.football.commonModels.model.AppTeamDetails
import com.football.league.App
import com.football.league.databinding.FragmentTeamDetailsBinding
import com.football.league.di.AppComponent
import com.football.league.utils.gone
import com.football.league.utils.visible
import kotlinx.coroutines.launch

class TeamDetailsFragment : Fragment() {
    private val viewModel: TeamDetailsViewModel by viewModels {
        val appComponent = (activity?.application as App).getBaseComponent()
        (appComponent as AppComponent).viewModelsFactory()
    }

    private var _binding: FragmentTeamDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var playersAdapter: PlayersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTeamDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val competitionId = requireArguments().getLong("competitionId", 0)
        val teamId = requireArguments().getLong("teamId", 0)

        viewModel.getTeamDetails(competitionId, teamId)

        initRv()
        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }

        fetchData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        viewModel.onDestroyView()
    }

    private fun initRv() {
        playersAdapter = PlayersAdapter()

        binding.teamsRv.adapter = playersAdapter
        binding.teamsRv.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

    }

    private fun fetchData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is TeamDetailsViewModel.CompetitionDetailsUiState.IsLoading -> showLoading()

                        is TeamDetailsViewModel.CompetitionDetailsUiState.Error -> showMessage(
                            state.t ?: "NoContent"
                        )

                        is TeamDetailsViewModel.CompetitionDetailsUiState.Success -> showData(
                            state.data
                        )
                    }

                }
            }
        }
    }

    private fun showData(data: AppTeamDetails) {
        binding.loader.gone()
        binding.teamDetailsLayout.visible()

        Glide.with(binding.teamIv)
            .load(data.logo)
            .into(binding.teamIv)

        binding.teamTxt.text = data.name

        val venue = data.venue
        if (venue.isNotEmpty())
            binding.teamVenueTxt.text = venue
        else
            binding.teamVenueContainer.gone()

        val founded = data.founded
        if (founded.isNotEmpty())
            binding.teamFoundedTxt.text = founded
        else
            binding.teamFoundedContainer.gone()


        val coach = data.coach.name
        if (coach.isNotEmpty())
            binding.teamCoachTxt.text = coach
        else
            binding.teamCoachLabel.gone()

        binding.playersContainer.visible()
        playersAdapter.submitList(data.players)
    }

    private fun showMessage(message: String) {
        binding.loader.gone()
        binding.messageTxt.visible()
        binding.messageTxt.text = message
    }

    private fun showLoading() {
        binding.loader.visible()
        binding.messageTxt.gone()
    }
}