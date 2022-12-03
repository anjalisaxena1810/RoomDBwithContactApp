package com.example.roomdbwithcontactapp

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdbwithcontactapp.databinding.ActivityMainBinding
import com.example.roomdbwithcontactapp.databinding.CreateContactDialougeBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var factory: AppViewModelFactory
    private lateinit var viewModel : AppViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val appDao = AppDatabase.getInstance(this).appDao
        factory = AppViewModelFactory(AppRepository(appDao))
        viewModel = ViewModelProvider(this, factory)[AppViewModel::class.java]







        binding.rcwView.layoutManager = LinearLayoutManager(this)
        viewModel.user.observe(this, Observer {
            val adapter = AppRecyclerviewAdapter(it, object:OnItemClickListener{
                override fun onItemClick(position: Int, user: User) {
                    val intent= Intent(this@MainActivity,UpdateActivity::class.java)
                    intent.putExtra("USER_DATA", Gson().toJson(user))
                    startActivity(intent)
                }

            })
            binding.rcwView.adapter = adapter
        })




            binding.addContact.setOnClickListener {

            val dialogBinding : CreateContactDialougeBinding = DataBindingUtil.inflate(LayoutInflater.from(this),R.layout.create_contact_dialouge, null, false)
            val dialog = Dialog(this)
            dialog.setContentView(dialogBinding.root)

            val manager = WindowManager.LayoutParams()
            manager.width = WindowManager.LayoutParams.MATCH_PARENT
            manager.height = WindowManager.LayoutParams.WRAP_CONTENT
            dialog.window!!.attributes = manager
            dialog.show()

            dialogBinding.btnCreate.setOnClickListener {
                val user= User(0,dialogBinding.etFname.text.toString(),dialogBinding.etLname.text.toString(), dialogBinding.etNumber.text.toString())
                viewModel.saveData(user)
                dialog.dismiss()


            }
            dialogBinding.btnClose.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.contact_option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.it_delete_all ->{
                viewModel.deleteAllUser()

            }
        }
        return super.onOptionsItemSelected(item)
    }
}

