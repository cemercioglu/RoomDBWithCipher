package com.asis.roomdbwithcipher.ui.main

import androidx.lifecycle.*
import com.asis.roomdbwithcipher.data.model.User
import com.asis.roomdbwithcipher.data.repository.UserRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException


/**
 * Created by cemercioglu on 25.08.2021.
 * Copyright (c) 2021  Asis Elektronik Bilişim Sis. A.Ş. All rights reserved.
 */
class MainViewModel(private val userRepository: UserRepository) : ViewModel() {
    val allUsers: LiveData<List<User>> = userRepository.allUsers.asLiveData()

    fun insertUser(user: User) = viewModelScope.launch {
        userRepository.insert(user)
    }
}

class MainViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}