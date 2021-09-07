package com.asis.roomdbwithcipher.data.repository

import androidx.annotation.WorkerThread
import com.asis.roomdbwithcipher.data.db.dao.UserDao
import com.asis.roomdbwithcipher.data.model.User
import kotlinx.coroutines.flow.Flow


/**
 * Created by cemercioglu on 25.08.2021.
 * Copyright (c) 2021  Asis Elektronik Bilişim Sis. A.Ş. All rights reserved.
 */
class UserRepository(private val userDao: UserDao) {

    val allUsers: Flow<List<User>> = userDao.getUsersByOrderName()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }
}
