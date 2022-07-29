package com.joshephez.githubuser.repo

import com.joshephez.githubuser.network.RetroServiceInstance
import javax.inject.Inject

class UsersSearchRepo
@Inject constructor(private val retroInstance: RetroServiceInstance) {
    suspend fun searchUsers(keyword: String) = retroInstance.searchUsers(keyword)
}