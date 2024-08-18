package com.football.league.ui.home

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
import com.football.commonModels.model.AppCompetition
import com.football.league.App
import com.football.league.R
import com.football.league.databinding.FragmentCompetitionsBinding
import com.football.league.di.AppComponent
import com.football.league.utils.gone
import com.football.league.utils.visible
import kotlinx.coroutines.launch


class CompetitionsFragment : Fragment() {
    private var _binding: FragmentCompetitionsBinding? = null
    private val binding get() = _binding!!

    private lateinit var competitionsAdapter: CompetitionsAdapter

    private val viewModel: CompetitionsViewModel by viewModels {
        val appComponent = (activity?.application as App).getBaseComponent()
        (appComponent as AppComponent).viewModelsFactory()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCompetitionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        competitionsAdapter = CompetitionsAdapter()
        competitionsAdapter.setOnItemClick(object : CompetitionsAdapter.OnItemClicked {
            override fun onClick(competitionId: Long) {
                navToCompetitionDetails(competitionId)
            }

        })

        binding.competitionsRv.adapter = competitionsAdapter
        binding.competitionsRv.layoutManager = LinearLayoutManager(requireContext())


        viewModel.getCompetitionsList()
        fetchData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        viewModel.onDestroyView()
    }

    private fun navToCompetitionDetails(competitionId: Long) {
        val bundle = Bundle()
        bundle.putLong("id", competitionId)

        Navigation.findNavController(binding.root).navigate(
            R.id.action_competitionsFragment_to_competitionDetailsFragment,
            bundle
        )
    }

    private fun fetchData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is CompetitionsViewModel.CompetitionsUiState.IsLoading -> showLoading()

                        is CompetitionsViewModel.CompetitionsUiState.Empty -> showMessage("NoContent")

                        is CompetitionsViewModel.CompetitionsUiState.Error -> showMessage(
                            state.t ?: "NoContent"
                        )

                        is CompetitionsViewModel.CompetitionsUiState.Success -> showData(state.data)
                    }

                }
            }
        }
    }

    private fun showData(data: List<AppCompetition>) {
        binding.loader.visible()
        binding.messageTxt.gone()
        competitionsAdapter.submitList(data)
    }

    private fun showMessage(message: String) {
        binding.loader.gone()
        binding.messageTxt.visible()
        binding.messageTxt.text = message
    }

    private fun showLoading() {
        binding.messageTxt.gone()
        binding.loader.visible()
    }
}