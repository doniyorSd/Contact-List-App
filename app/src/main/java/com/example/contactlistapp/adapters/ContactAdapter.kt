package com.example.contactlistapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistapp.R
import com.example.contactlistapp.adapters.models.Contact
import com.example.contactlistapp.databinding.ItemContactBinding

class ContactAdapter(var list: ArrayList<Contact>,var contactClickInterface: ContactClickInterface) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int, contact: Contact) {
            val binding = ItemContactBinding.bind(itemView)
            binding.name.text = contact.name
            binding.firstCharName.text = contact.name.substring(0, 1)
            binding.root.setOnClickListener {
                contactClickInterface.rootClickListener(position,contact)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.onBind(position, list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ContactClickInterface {
        fun rootClickListener(position: Int,contact: Contact)
    }
}