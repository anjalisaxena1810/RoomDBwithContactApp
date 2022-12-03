package com.example.roomdbwithcontactapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AppViewModel(private val appRepository: AppRepository): ViewModel() {

    fun saveData(user: User) {
        viewModelScope.launch {
            appRepository.saveUser(user)
        }
    }

    fun updateData(user: User) {
        viewModelScope.launch {
            appRepository.updateData(user)
        }
    }

    fun deleteSingleUser(user: User) {
        viewModelScope.launch {
            appRepository.deleteSingleUser(user)
        }
    }

    fun deleteAllUser() {
        viewModelScope.launch {
            appRepository.deleteAllUser()
        }
    }

    val user  = appRepository.user
}
