package com.example.roomdbwithcontactapp

import android.Manifest
import android.app.Activity

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbwithcontactapp.databinding.LayoutCustomViewBinding


class AppRecyclerviewAdapter(private val userList: List<User>,private val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<AppRecyclerviewAdapter.CustomViewHolder>() {
        inner class CustomViewHolder(var layoutCustomViewBinding: LayoutCustomViewBinding): RecyclerView.ViewHolder(layoutCustomViewBinding.root){

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            var layoutCustomViewBinding:LayoutCustomViewBinding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.layout_custom_view,parent,false)
            return CustomViewHolder(layoutCustomViewBinding)
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
////
//            var contacts = userList.get(position)
//            val requestPermissionList = arrayOf(Manifest.permission.CALL_PHONE)
//            val REQUEST_CODE = 1

            val user = userList.get(position)
            holder.layoutCustomViewBinding.tvFname.text = user.fname
            holder.layoutCustomViewBinding.tvLname.text = user.lname
            holder.layoutCustomViewBinding.tvNumber.text = user.mobno
//
//            val mobileNo:String = contacts.mobno
//            val intent = Intent(Intent.ACTION_DIAL, Uri.parse( "tel:$mobileNo"))
//            holder.layoutCustomViewBinding.tvDial.setOnClickListener{
//                context.startActivity(intent)
//            }
//
//            holder.layoutCustomViewBinding.tvCall.setOnClickListener{
//                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
//                    val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$mobileNo"))
//                    context.startActivity(intent)
//                }else{
//                    ActivityCompat.requestPermissions(context as Activity, requestPermissionList,REQUEST_CODE)
//                }
//            }
            holder.itemView.setOnClickListener {
                onItemClickListener.onItemClick(position,user)
            }
        }
        override fun getItemCount(): Int {
            return userList.size
        }
    }
