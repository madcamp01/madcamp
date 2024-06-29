package com.example.madcamp01.first

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.R
import com.example.madcamp01.DB.Entities.Contact

class ContactAdapter(private val contacts: List<Contact>,
                     private val onItemClick: (Contact) -> Unit)
    : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)
        holder.itemView.setOnClickListener {
            onItemClick(contact)
        }
    }

    override fun getItemCount(): Int = contacts.size

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contactImage: ImageView = itemView.findViewById(R.id.contact_image)
        private val contactName: TextView = itemView.findViewById(R.id.contact_name)
        private val contactStatus: TextView = itemView.findViewById(R.id.contact_status)

        fun bind(contact: Contact) {
            contactImage.setImageURI(contact.profilePicture)
            contactName.text = contact.personName
            contactStatus.text = contact.memo
        }
    }
}
