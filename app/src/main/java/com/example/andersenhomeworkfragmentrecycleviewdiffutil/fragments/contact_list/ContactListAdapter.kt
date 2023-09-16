package com.example.andersenhomeworkfragmentrecycleviewdiffutil.fragments.contact_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.R
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.repository.Contact
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.repository.ContactsRepo

class ContactListAdapter() :
    RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder>() {
        var contactsList: List<Contact> = emptyList()
        set(newValue) {
            val diffCallback = ContactDiffUtil(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    inner class ContactListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val contactName = view.findViewById<TextView>(R.id.contact_name)
        val image = view.findViewById<ImageView>(R.id.contact_avatar)
        val layout = view.findViewById<View>(R.id.contact_avatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.contact_list_item, parent, false)
        layout.accessibilityDelegate = View.AccessibilityDelegate()
        return ContactListViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {

        val item = contactsList[position]
        holder.contactName.text = item.name
        holder.contactName.setOnClickListener {
            val action = ContactListFragmentDirections.actionContactListFragmentToContactInfoFragment(contactIndex = position.toString())
            holder.view.findNavController().navigate(action)
        }
        holder.contactName.setOnLongClickListener {
            removeContact(item.id)
            true
        }
//        holder.layout.setOnClickListener {
//            val action =
//                ContactListFragmentDirections.actionContactListFragmentToContactInfoFragment(
//                    contactIndex = position.toString()
//                )
//            holder.view.findNavController().navigate(action)
//        }
    }

    private fun removeContact(contactId: Int) {
        val newList = ArrayList(contactsList)
        newList.removeIf { it.id == contactId }
        contactsList = newList
        ContactsRepo.contactsList.removeIf { it.id == contactId }
    }

    fun search(query: String) {
        val newList = ContactsRepo.contactsList.filter { it.name.lowercase().contains(query.lowercase()) }
        contactsList = newList
    }
}

