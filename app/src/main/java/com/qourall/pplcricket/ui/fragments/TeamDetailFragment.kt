package com.qourall.pplcricket.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.qourall.pplcricket.PPLApplication
import com.qourall.pplcricket.R
import com.qourall.pplcricket.ui.adapter.MemberAdapter
import com.qourall.pplcricket.ui.adapter.TeamsAdapter
import com.qourall.pplcricket.ui.viewModels.MembersViewModel
import com.qourall.pplcricket.ui.viewModels.MembersViewModelFactory
import com.qourall.pplcricket.ui.viewModels.TeamsViewModel
import com.qourall.pplcricket.ui.viewModels.TeamsViewModelFactory

class TeamDetailFragment : Fragment() {

    private val args by navArgs<TeamDetailFragmentArgs>()
    private val viewModel : MembersViewModel by activityViewModels {
        MembersViewModelFactory((
                activity?.application as PPLApplication).database.memberDao()
        )
    }
    lateinit var recyclerView: RecyclerView
    lateinit var fab : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.members_list)
        fab = view.findViewById(R.id.fab_addMember)

        addMember()

        viewModel.getAllMembers(args.teamName)
        viewModel.allMembers.observe(viewLifecycleOwner, {
            val adapter = MemberAdapter(requireContext(), it)
            recyclerView.adapter = adapter
        })

    }

    private fun addMember() {
        fab.setOnClickListener {
            val action = TeamDetailFragmentDirections.actionTeamDetailFragmentToAddMemberFragment(args.teamName)
            Navigation.findNavController(it).navigate(action)
        }
    }

}