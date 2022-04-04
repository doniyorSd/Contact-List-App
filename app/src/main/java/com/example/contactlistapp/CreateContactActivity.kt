package com.example.contactlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contactlistapp.adapters.models.Contact
import com.example.contactlistapp.databinding.ActivityCreateContactBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CreateContactActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gson = Gson()
        var list = ArrayList<Contact>()
        val shared = getSharedPreferences("shared", MODE_PRIVATE)
        val edit = shared.edit()
//        // 4 ta contact
        val str = shared.getString("contacts", "")
        if (str != "") {
            val type = object : TypeToken<ArrayList<Contact>>() {}.type
            list = gson.fromJson(str, type)
        }
        binding.btnSave.setOnClickListener {
            val name = binding.name.text.toString()
            val number = binding.number.text.toString()
            list.add(Contact(name, number))
            val strGson = gson.toJson(list)
            edit.putString("contacts", strGson)
            edit.apply()
            finish()
        }

        // 3 ta ociradi list
    }
}