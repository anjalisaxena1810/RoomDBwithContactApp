package com.example.roomdbwithcontactapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppDao {
@Insert
suspend fun insertUser(vararg user:User)

@Query("SELECT* FROM user")
 fun getAllUser():LiveData<List<User>>

@Delete
suspend fun deleteUser(user: User)

@Update
suspend fun updateUser(user: User)

@Query("DELETE FROM user")
suspend fun deleteAllUser()


}