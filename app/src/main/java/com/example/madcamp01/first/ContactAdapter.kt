package com.example.madcamp01.first

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.databinding.ContactItemBinding
import com.example.madcamp01.DB.contacts.Contact


class ContactAdapter(private var contactList: List<Contact>, private val onItemClick: (Contact) -> Unit) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = contactList[position]
        holder.bind(currentContact)
    }

    override fun getItemCount() = contactList.size

    inner class ContactViewHolder(private val binding: ContactItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.contactName.text = contact.name
            binding.contactStatus.text = contact.status
            binding.contactImage.setImageResource(contact.imageResId)

            itemView.setOnClickListener {
                onItemClick(contact)
            }
        }

    }
    fun updateData(newContacts: List<Contact>) {
        contactList = newContacts
        notifyDataSetChanged() // 데이터 변경을 어댑터에 알림
    }
}