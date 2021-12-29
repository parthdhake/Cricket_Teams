package com.qourall.pplcricket.ui.viewModels

import androidx.lifecycle.*
import com.qourall.pplcricket.data.localDB.MemberDao
import com.qourall.pplcricket.data.localDB.TeamDao
import com.qourall.pplcricket.data.models.Team
import com.qourall.pplcricket.data.models.TeamMember
import kotlinx.coroutines.launch

class MembersViewModel(private val memberDao: MemberDao) : ViewModel() {

    private var _allMembers = MutableLiveData<List<TeamMember>>()

    val allMembers : LiveData<List<TeamMember>>
        get() = _allMembers

    fun insertMember(member : TeamMember){
        viewModelScope.launch {
            memberDao.insertMemberDetails(member)
        }
    }

    fun getAllMembers(team_name: String){
        viewModelScope.launch {
            _allMembers.value = memberDao.getTeamMembers(team_name)
        }
    }

}


class MembersViewModelFactory(private val memberDao: MemberDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MembersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MembersViewModel(memberDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}