package com.joshephez.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joshephez.githubuser.model.UsersItem
import com.joshephez.githubuser.model.UsersResponseItem
import com.joshephez.githubuser.model.UsersSearchResponse
import com.joshephez.githubuser.repo.UsersRepo
import com.joshephez.githubuser.repo.UsersSearchRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val userRepo: UsersRepo,private val searchUsersRepo : UsersSearchRepo): ViewModel(){

    private val _response = MutableLiveData<List<UsersResponseItem>>()
    private val _searchResult = MutableLiveData<UsersSearchResponse>()
    val responseUsers: LiveData<List<UsersResponseItem>>
        get() =_response
    val responseSearch :LiveData<UsersSearchResponse>
    get() = _searchResult

    init{
        getAllUsers()
    }
    private fun getAllUsers() = viewModelScope.launch{
        userRepo.getUsers().let {
            response ->
            if(response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.d("failed messages","something error: ${response.code()}")
            }
        }
    }

      fun doSearchUsers(keyword :String) =viewModelScope.launch {
        searchUsersRepo.searchUsers(keyword).let {
                response ->
            if(response.isSuccessful){
                _searchResult.postValue(response.body())
            }else{
                Log.d("failed messages","something error: ${response.code()}")
            }
        }

    }



}