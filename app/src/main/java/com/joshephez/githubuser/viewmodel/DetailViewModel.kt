package com.joshephez.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joshephez.githubuser.model.RepoResponse
import com.joshephez.githubuser.model.UserDetailResponse
import com.joshephez.githubuser.repo.DetailRepo
import com.joshephez.githubuser.repo.UsersRepo
import com.joshephez.githubuser.repo.UsersSearchRepo
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailRepo: DetailRepo): ViewModel(){
    private val _detailResponse= MutableLiveData<UserDetailResponse>()
    private val _reposResponse = MutableLiveData<RepoResponse>()
    val detailResponse :LiveData<UserDetailResponse>
    get() = _detailResponse
    val reposResponse :LiveData<RepoResponse>
    get()=_reposResponse

    lateinit var  name :String



    fun getUserByName(username:String)=viewModelScope.launch{

        detailRepo.getUsersByName(username).let {
            response ->
            if(response.isSuccessful){
                _detailResponse.postValue(response.body())

            }else{
                Log.d("failed messages","something error on detailuser: ${response.code()}")
            }
        }

    }

    fun getRepoByName(username: String)=viewModelScope.launch {
        detailRepo.getReposByName(username).let{
            response ->
            if(response.isSuccessful){
                _reposResponse.postValue(response.body())
            }else{
                Log.d("failed messages","something error on repos: ${response.code()}")
            }
        }

    }
}