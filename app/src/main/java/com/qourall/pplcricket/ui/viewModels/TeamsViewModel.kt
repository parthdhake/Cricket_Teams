package com.qourall.pplcricket.ui.viewModels

import androidx.lifecycle.*
import com.qourall.pplcricket.data.localDB.TeamDao
import com.qourall.pplcricket.data.models.Team
import kotlinx.coroutines.launch

class TeamsViewModel(private val teamDao: TeamDao) : ViewModel() {

    private var _allTeams = MutableLiveData<List<Team>>()

    val allTeams : LiveData<List<Team>>
        get() = _allTeams

    fun insertTeam(team : Team){
        viewModelScope.launch {
            teamDao.insertTeamDetails(team)
        }
    }

    fun getAllTeams(){
        viewModelScope.launch {
            _allTeams.value = teamDao.getTeams()
        }
    }

}


class TeamsViewModelFactory(private val teamDao: TeamDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TeamsViewModel(teamDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}