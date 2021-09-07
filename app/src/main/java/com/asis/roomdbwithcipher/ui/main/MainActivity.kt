package com.asis.roomdbwithcipher.ui.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asis.roomdbwithcipher.App
import com.asis.roomdbwithcipher.data.model.User
import com.asis.roomdbwithcipher.ui.newuser.NewUserActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import roomdbwithcipher.R

class MainActivity : AppCompatActivity() {
    private val newUserActivityRequestCode = 1

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mainViewModel.allUsers.observe(this) { users ->
            users.let {
                adapter.submitList(it)
            }
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewUserActivity::class.java)
            startActivityForResult(intent, newUserActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newUserActivityRequestCode && resultCode == Activity.RESULT_OK) {
            var firstName = ""
            var lastName = ""
            intentData?.getStringExtra(NewUserActivity.EXTRA_REPLY_FIRST_NAME)?.let { reply ->
                firstName = reply
            }
            intentData?.getStringExtra(NewUserActivity.EXTRA_REPLY_LAST_NAME)?.let { reply ->
                lastName = reply
            }
            mainViewModel.insertUser(User( firstName, lastName))

        } else {
            Toast.makeText(
                applicationContext,
                "EMPTY USER NOT SAVED",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}