package com.joshephez.githubuser.repo

import com.joshephez.githubuser.network.RetroServiceInstance
import javax.inject.Inject

class UsersRepo
@Inject constructor(private val retroServiceInstance: RetroServiceInstance) {
suspend fun getUsers() = retroServiceInstance.getUsers()
}