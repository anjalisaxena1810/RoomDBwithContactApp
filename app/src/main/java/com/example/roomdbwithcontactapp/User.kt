package com.example.roomdbwithcontactapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(

@PrimaryKey(autoGenerate = true)
  @ColumnInfo(name="ID")
  var id:Int,
@ColumnInfo(name = "FirstName")
   var fname :String,
@ColumnInfo(name =" LastName")
var lname:String,
@ColumnInfo(name = "MobileNo")
var mobno :String
)
