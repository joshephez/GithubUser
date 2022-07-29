package com.joshephez.githubuser.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.joshephez.githubuser.adapter.ReposAdapter
import com.joshephez.githubuser.adapter.UsersAdapter
import com.joshephez.githubuser.adapter.UsersSearchAdapter
import com.joshephez.githubuser.config.Constant

import com.joshephez.githubuser.config.Constant.replaceNull
import com.joshephez.githubuser.databinding.ActivityDetailBinding
import com.joshephez.githubuser.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding :ActivityDetailBinding
    private lateinit var userName :String
    private val viewModel : DetailViewModel by viewModels()
    private lateinit var reposAdapter: ReposAdapter


     override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
         binding = ActivityDetailBinding.inflate(layoutInflater)
         setContentView(binding.root)
         userName = intent.getStringExtra("USER_NAME").toString()

        viewModel.getUserByName(userName)
         viewModel.getRepoByName(userName)
         initData()


    }

    private fun initData() {
        viewModel.detailResponse.observe(this) { listUsers ->
            binding.apply {
                Glide.with(imgUserDetail)
                    .load(listUsers.avatar_url)
                    .into(imgUserDetail)

                tvNameDetail.text = listUsers.login
                tvTwitterDetail.text = "@${listUsers.twitter_username.replaceNull()}"
                tvDescDetail.text = listUsers.company.replaceNull()
                tvFollowerCount.text = Constant.countingConvert(listUsers.followers.toLong()).toString()
                tvFollowingCount.text =
                    Constant.countingConvert(listUsers.following.toLong()).toString()
                tvLocationDetail.text = listUsers.location.replaceNull()
                tvEmailDetail.text = listUsers.email.replaceNull()


            }
        }
        initRecycler()
    }

        private fun initRecycler(){
            reposAdapter = ReposAdapter()
            binding.recyclerRepos.apply{
                adapter = reposAdapter
                layoutManager= LinearLayoutManager(this@DetailActivity,)

            }
            viewModel.reposResponse.observe(this) { listUsers ->
                reposAdapter.repos = listUsers
            }
        }
    }






