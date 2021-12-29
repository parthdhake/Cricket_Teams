package com.qourall.pplcricket.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.qourall.pplcricket.PPLApplication
import com.qourall.pplcricket.R
import com.qourall.pplcricket.data.models.Team
import com.qourall.pplcricket.ui.viewModels.TeamsViewModel
import com.qourall.pplcricket.ui.viewModels.TeamsViewModelFactory

class AddTeamFragment : Fragment() {

    private val viewModel : TeamsViewModel by activityViewModels {
        TeamsViewModelFactory((
                activity?.application as PPLApplication).database.teamDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn = view.findViewById<MaterialButton>(R.id.add_btn)




        btn.setOnClickListener {

            val name = view.findViewById<TextInputLayout>(R.id.addTeamName).editText?.text.toString()
            val city = view.findViewById<TextInputLayout>(R.id.addCity).editText?.text.toString()
            val coach = view.findViewById<TextInputLayout>(R.id.addCoach).editText?.text.toString()

            val newTeam = Team(team_name = name, city = city, team_coach = coach, team_owner = "Hello")
            viewModel.insertTeam(team = newTeam)
        }
    }

}