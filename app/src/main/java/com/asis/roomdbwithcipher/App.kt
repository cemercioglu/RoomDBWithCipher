package com.asis.roomdbwithcipher

import android.app.Application
import com.asis.roomdbwithcipher.data.db.AppDatabase
import com.asis.roomdbwithcipher.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


/**
 * Created by cemercioglu on 25.08.2021.
 * Copyright (c) 2021  Asis Elektronik Bilişim Sis. A.Ş. All rights reserved.
 */
class App : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { UserRepository(database.userDao()) }
}