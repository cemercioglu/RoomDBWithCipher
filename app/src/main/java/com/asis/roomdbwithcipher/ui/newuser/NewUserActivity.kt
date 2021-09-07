package com.asis.roomdbwithcipher.ui.newuser

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import roomdbwithcipher.R

class NewUserActivity : AppCompatActivity() {

    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText

    companion object {
        const val EXTRA_REPLY_FIRST_NAME = "com.asis.roomdbwithcipher.ui.newuser.FIRST_NAME"
        const val EXTRA_REPLY_LAST_NAME = "com.asis.roomdbwithcipher.ui.newuser.LAST_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)
        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)

        val saveButton = findViewById<Button>(R.id.btnSave)
        saveButton.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(etFirstName.text) || TextUtils.isEmpty(etLastName.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val firstName = etFirstName.text.toString()
                val lastName = etLastName.text.toString()
                replyIntent.putExtra(EXTRA_REPLY_FIRST_NAME, firstName)
                replyIntent.putExtra(EXTRA_REPLY_LAST_NAME, lastName)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}