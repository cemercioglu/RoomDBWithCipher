package com.asis.roomdbwithcipher.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asis.roomdbwithcipher.data.model.User
import kotlinx.coroutines.flow.Flow


/**
 * Created by cemercioglu on 25.08.2021.
 * Copyright (c) 2021  Asis Elektronik Bilişim Sis. A.Ş. All rights reserved.
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun getUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Query("SELECT * FROM user ORDER BY first_name ASC")
    fun getUsersByOrderName(): Flow<List<User>>
}