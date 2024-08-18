package com.football.league.ui.competitionDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.football.commonModels.model.AppCompetitionDetails
import com.football.commonModels.model.AppCompetitionTeam
import com.football.league.App
import com.football.league.R
import com.football.league.databinding.FragmentCompetitionDetailsBinding
import com.football.league.di.AppComponent
import com.football.league.utils.gone
import com.football.league.utils.visible
import kotlinx.coroutines.launch

class CompetitionDetailsFragment : Fragment() {
    private val viewModel: CompetitionDetailsViewModel by viewModels {
        val appComponent = (activity?.application as App).getBaseComponent()
        (appComponent as AppComponent).viewModelsFactory()
    }

    private var _binding: FragmentCompetitionDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var teamsAdapter: TeamsAdapter

    private var competitionId: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCompetitionDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        competitionId = requireArguments().getLong("id", 0)

        viewModel.getCompetitionsList(competitionId)

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
        teamsAdapter = TeamsAdapter()
        teamsAdapter.setOnItemClick(object : TeamsAdapter.OnItemClicked {
            override fun onClick(teamId: Long) {
                navToTeamDetailsDetails(teamId)
            }

        })

        binding.teamsRv.adapter = teamsAdapter
        binding.teamsRv.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
    }

    private fun fetchData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is CompetitionDetailsViewModel.CompetitionDetailsUiState.IsLoading -> showLoading()

                        is CompetitionDetailsViewModel.CompetitionDetailsUiState.Error -> showMessage(
                            state.t ?: "NoContent"
                        )

                        is CompetitionDetailsViewModel.CompetitionDetailsUiState.Success -> showData(
                            state.data
                        )
                    }

                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiTeamState.collect { state ->
                    when (state) {
                        is CompetitionDetailsViewModel.CompetitionDetailsUiState.IsLoading -> showLoading()

                        is CompetitionDetailsViewModel.CompetitionDetailsUiState.Error -> {
                            binding.competitionTeamsContainer.visibility = View.GONE
                        }

                        is CompetitionDetailsViewModel.CompetitionDetailsUiState.Success -> showTeamData(
                            state.data
                        )
                    }

                }
            }
        }
    }

    private fun showData(data: AppCompetitionDetails) {
        binding.competitionDetailsLayout.visible()

        binding.loader.gone()
        binding.messageTxt.gone()

        binding.competitionTxt.text = data.name

        Glide.with(binding.competitionIv)
            .load(data.logo)
            .into(binding.competitionIv)

        binding.competitionStartTimeTxt.text = data.currentSeason.startDate
        binding.competitionEndTimeTxt.text = data.currentSeason.endDate

        if (data.currentSeason.winnerTeam != null) {
            val winner = data.currentSeason.winnerTeam!!
            binding.competitionWinnerTxt.text = winner.name

            Glide.with(binding.winnerTeamLogo)
                .load(winner.logo)
                .into(binding.winnerTeamLogo)
        } else {
            binding.competitionWinnerContainer.gone()
        }

    }

    private fun showTeamData(teamsDetails: List<AppCompetitionTeam>) {
        binding.loader.gone()
        binding.competitionTeamsContainer.visible()
        teamsAdapter.submitList(teamsDetails)
    }

    private fun showMessage(message: String) {
        binding.loader.gone()
        binding.competitionDetailsLayout.gone()
        binding.messageTxt.visible()
        binding.messageTxt.text = message
    }

    private fun showLoading() {
        binding.loader.visible()
        binding.messageTxt.gone()
    }

    private fun navToTeamDetailsDetails(teamId: Long) {
        val bundle = Bundle()
        bundle.putLong("teamId", teamId)
        bundle.putLong("competitionId", competitionId)

        Navigation.findNavController(binding.root).navigate(
            R.id.action_competitionDetailsFragment_to_teamDetailsFragment,
            bundle
        )

    }
}