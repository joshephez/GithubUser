package com.joshephez.githubuser.repo

import com.joshephez.githubuser.network.RetroServiceInstance
import javax.inject.Inject

class DetailRepo
@Inject constructor(private val retroInstance: RetroServiceInstance) {
   suspend  fun getUsersByName(username:String) = retroInstance.getUsersByName(username)
   suspend  fun getReposByName(username:String) = retroInstance.getReposByName(username)

}