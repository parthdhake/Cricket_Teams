package com.qourall.pplcricket.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.qourall.pplcricket.R
import com.qourall.pplcricket.data.models.TeamMember
import com.qourall.pplcricket.ui.fragments.TeamDetailFragmentDirections
import com.qourall.pplcricket.ui.fragments.TeamsFragmentDirections

class MemberAdapter(val context: Context, private val data: List<TeamMember>) : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        return MemberViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.member_item, parent,false))
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {

        holder.name.text = data[position].name
        holder.age.text = data[position].age.toString()
        holder.role.text = data[position].role


        holder.itemView.setOnClickListener {
            val action = TeamDetailFragmentDirections.actionTeamDetailFragmentToMemberDetailFragment(data[position].name)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MemberViewHolder(v : View) : RecyclerView.ViewHolder(v){
        val name: TextView = v.findViewById(R.id.member_name)
        val age: TextView = v.findViewById(R.id.member_age)
        val role: TextView = v.findViewById(R.id.member_role)

    }
}