package com.example.contactlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contactlistapp.adapters.models.Contact
import com.example.contactlistapp.databinding.ActivityAboutContactBinding

class AboutContactActivity : AppCompatActivity() {
    lateinit var binding : ActivityAboutContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val contact = intent.getSerializableExtra("contact") as Contact
        binding.tv.text = "${contact.name}\n${contact.number}"
    }
}