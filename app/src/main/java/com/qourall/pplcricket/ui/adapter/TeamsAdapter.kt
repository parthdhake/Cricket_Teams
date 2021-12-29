package com.qourall.pplcricket.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.qourall.pplcricket.R
import com.qourall.pplcricket.data.models.Team
import com.qourall.pplcricket.ui.fragments.TeamsFragmentDirections

class TeamsAdapter(val context: Context, private val data: List<Team>) : RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        return TeamsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.team_item, parent,false))
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {

        holder.name.text = data[position].team_name
        holder.city.text = data[position].city
        holder.coach.text = data[position].team_coach


        holder.itemView.setOnClickListener {
            val action = TeamsFragmentDirections.actionTeamsFragmentToTeamDetailFragment(data[position].team_name)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class TeamsViewHolder(v : View) : RecyclerView.ViewHolder(v){
        val name: TextView = v.findViewById(R.id.team_name)
        val city: TextView = v.findViewById(R.id.team_city)
        val coach: TextView = v.findViewById(R.id.team_coach)

    }
}