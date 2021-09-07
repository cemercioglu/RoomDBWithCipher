package com.asis.roomdbwithcipher.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import roomdbwithcipher.R
import com.asis.roomdbwithcipher.data.model.User


/**
 * Created by cemercioglu on 26.08.2021.
 * Copyright (c) 2021  Asis Elektronik Bilişim Sis. A.Ş. All rights reserved.
 */
class UserListAdapter : ListAdapter<User, UserListAdapter.UserViewHolder>(UserComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.firstName + " " + current.lastName)
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            userItemView.text = text
        }

        companion object {

            fun create(parent: ViewGroup): UserViewHolder {
                val view: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
                return UserViewHolder(view)
            }
        }
    }

    class UserComparator : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return (oldItem.firstName + " " + oldItem.lastName) == (newItem.firstName + " " + newItem.lastName)
        }
    }
}
