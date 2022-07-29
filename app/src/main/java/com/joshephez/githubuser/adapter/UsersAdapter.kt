package com.joshephez.githubuser.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joshephez.githubuser.databinding.UserItemBinding
import com.joshephez.githubuser.model.UsersResponseItem
import com.joshephez.githubuser.ui.DetailActivity

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: UserItemBinding ):
            RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<UsersResponseItem>(){
        override fun areItemsTheSame(
            oldItem: UsersResponseItem,
            newItem: UsersResponseItem
        ): Boolean {
            return oldItem.id ==newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UsersResponseItem,
            newItem: UsersResponseItem
        ): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var users:List<UsersResponseItem>
    get()= differ.currentList
    set(value) {
        differ.submitList(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(UserItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentUsers = users[position]


        holder.binding.apply {
            tvUserName.text =currentUsers.login
            tvMediaSocial.text = "@"+currentUsers.login
            tvEmail.text = currentUsers.login+"@gmail.com"
            tvUserDesc.text = currentUsers.following_url
            tvLocation.text = currentUsers.node_id+",${currentUsers.login}"
            Glide.with(thumbImage)
                .load(currentUsers.avatar_url)
                .into(thumbImage)


        }
        holder.itemView.setOnClickListener {v->
            val intent = Intent( v.context, DetailActivity::class.java)
            intent.putExtra("USER_NAME",currentUsers.login)
            v.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}