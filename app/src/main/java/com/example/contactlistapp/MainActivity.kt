package com.example.contactlistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import com.example.contactlistapp.adapters.ContactAdapter
import com.example.contactlistapp.adapters.models.Contact
import com.example.contactlistapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var list: ArrayList<Contact>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainAct", "OnResume: ")
        list = ArrayList()
        val shared = getSharedPreferences("shared", MODE_PRIVATE)
        val str = shared.getString("contacts","")
        val type = object :TypeToken<ArrayList<Contact>>(){}.type
        val gson = Gson()
        list = gson.fromJson(str,type)
        binding.add.setOnClickListener {
            val intent = Intent(this, CreateContactActivity::class.java)
            startActivity(intent)
        }
        contactAdapter = ContactAdapter(list, object : ContactAdapter.ContactClickInterface {
            override fun rootClickListener(position: Int, contact: Contact) {
                val intent = Intent(this@MainActivity, AboutContactActivity::class.java)
                intent.putExtra("contact", contact)
                startActivity(intent)
            }
        })
        binding.rv.adapter = contactAdapter
    }
}