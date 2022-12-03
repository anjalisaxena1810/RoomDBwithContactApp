package com.example.roomdbwithcontactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.roomdbwithcontactapp.databinding.ActivityUpdateBinding
import com.google.gson.Gson

class UpdateActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityUpdateBinding
    private lateinit var factory: AppViewModelFactory
    private lateinit var viewModel : AppViewModel
    lateinit var user:User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_update)
        val appDao = AppDatabase.getInstance(this).appDao
        factory = AppViewModelFactory(AppRepository(appDao))
        viewModel = ViewModelProvider(this, factory)[AppViewModel::class.java]
        user = Gson().fromJson(intent.getStringExtra("USER_DATA"), User::class.java)

        binding.etEdFname.setText(user.fname)
        binding.etEdLname.setText(user.lname)
        binding.etEdNumber.setText(user.mobno)

        binding.btnUpdate.setOnClickListener {
            viewModel.updateData(
                User(
                    user.id,
                    binding.etEdFname.text.toString(),
                    binding.etEdLname.text.toString(),
                    binding.etEdNumber.text.toString()
                )
            )
//
            Toast.makeText(this, "Record updated", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_contact_menu_option, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ed_delete -> {
                viewModel.deleteSingleUser(user)

                Toast.makeText(this,"Record Deleted",Toast.LENGTH_SHORT).show()
                finish()


            }
            R.id.ed_edit -> {
                binding.btnUpdate.visibility = View.VISIBLE



            }
        }
        return super.onOptionsItemSelected(item)
    }
}