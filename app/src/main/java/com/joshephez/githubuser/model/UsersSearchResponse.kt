package com.joshephez.githubuser.model

data class UsersSearchResponse(
    val incomplete_results: Boolean,
    val items: List<UsersItem>,
    val total_count: Int
)