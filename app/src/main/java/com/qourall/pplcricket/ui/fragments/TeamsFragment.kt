package com.qourall.pplcricket.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.qourall.pplcricket.PPLApplication
import com.qourall.pplcricket.R
import com.qourall.pplcricket.data.models.Team
import com.qourall.pplcricket.ui.adapter.TeamsAdapter
import com.qourall.pplcricket.ui.viewModels.TeamsViewModel
import com.qourall.pplcricket.ui.viewModels.TeamsViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsFragment : Fragment() {

    private val viewModel : TeamsViewModel by activityViewModels {
        TeamsViewModelFactory((
                activity?.application as PPLApplication).database.teamDao()
        )
    }

    lateinit var recyclerView: RecyclerView
    lateinit var fab : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.teams_list)
        fab = view.findViewById(R.id.fab_addTeam)

        addTeam()
        viewModel.getAllTeams()
        viewModel.allTeams.observe(viewLifecycleOwner, {
            val adapter = TeamsAdapter(requireContext(), it)
            recyclerView.adapter = adapter
        })
    }

    private fun addTeam() {
        fab.setOnClickListener {
            val action = TeamsFragmentDirections.actionTeamsFragmentToAddTeamFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}