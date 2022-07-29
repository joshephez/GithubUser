package com.joshephez.githubuser.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.joshephez.githubuser.adapter.UsersAdapter
import com.joshephez.githubuser.adapter.UsersSearchAdapter
import com.joshephez.githubuser.databinding.ActivityUsersBinding
import com.joshephez.githubuser.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_users.*

@AndroidEntryPoint
class UsersActivity :AppCompatActivity(){

    private lateinit var binding: ActivityUsersBinding
    private val viewModel : UsersViewModel by viewModels()
    private lateinit var usersAdapter : UsersAdapter
    private lateinit var usersSearchAdapter : UsersSearchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
        addTextListener()


    }

     private fun addTextListener(){
        etSearch.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                initRecyclerSearch()
                viewModel.doSearchUsers(etSearch.text.toString())
              return@OnKeyListener true
            }
            false
        })

        etSearch.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
            // i dont do search in this moment, because github rate limit API
                if(s.toString() == ""){
                    initRecycler()
                }
            }

        })


    }

    private fun initRecyclerSearch(){
        usersSearchAdapter = UsersSearchAdapter()
        binding.recyclerUser.apply{
            adapter = usersSearchAdapter
            layoutManager=LinearLayoutManager(this@UsersActivity,)

        }
        viewModel.responseSearch.observe(this) { listUsers ->
            usersSearchAdapter.users = listUsers.items
        }
    }


    private fun initRecycler(){
        usersAdapter = UsersAdapter()
        binding.recyclerUser.apply{
            adapter = usersAdapter
            layoutManager=LinearLayoutManager(this@UsersActivity,)

        }
        viewModel.responseUsers.observe(this) { listUsers ->
            usersAdapter.users = listUsers
        }
    }

}