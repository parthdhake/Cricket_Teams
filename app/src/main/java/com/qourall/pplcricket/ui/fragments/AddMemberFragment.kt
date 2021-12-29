package com.qourall.pplcricket.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.qourall.pplcricket.PPLApplication
import com.qourall.pplcricket.R
import com.qourall.pplcricket.data.models.TeamMember
import com.qourall.pplcricket.ui.viewModels.MembersViewModel
import com.qourall.pplcricket.ui.viewModels.MembersViewModelFactory

class AddMemberFragment : Fragment() {

    private val viewModel : MembersViewModel by activityViewModels {
        MembersViewModelFactory((
                activity?.application as PPLApplication).database.memberDao()
        )
    }
    private val args by navArgs<TeamDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_member, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn = view.findViewById<MaterialButton>(R.id.add_btn_member)

        btn.setOnClickListener {

            val name = view.findViewById<TextInputLayout>(R.id.addMemberName).editText?.text.toString()
            val age = view.findViewById<TextInputLayout>(R.id.addAge).editText?.text.toString()
            val role = view.findViewById<TextInputLayout>(R.id.addRole).editText?.text.toString()

            val newMember = TeamMember(name = name, team_name = args.teamName, age = age, role = role )
            viewModel.insertMember(member = newMember)
        }
    }
}