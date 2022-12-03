package com.example.roomdbwithcontactapp

class AppRepository (private val appDao:AppDao) {

    suspend fun saveUser(user:User){
        appDao.insertUser(user)
    }
    suspend fun updateData(user: User){
        appDao.updateUser(user)
    }

    suspend fun deleteSingleUser(user: User){
        appDao.deleteUser(user)
    }

    suspend fun deleteAllUser(){
        appDao.deleteAllUser()
    }



    val user = appDao.getAllUser()
}
