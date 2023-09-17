package com.example.andersenhomeworkfragmentrecycleviewdiffutil.fragments.contact_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.R
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.databinding.ContactListItemBinding
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.fragments.FragmentViewModel
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.repository.Contact
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.repository.ContactsRepo
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.utl.DeviceType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactListAdapter(
    context: Context
) :
    RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder>() {
    private val _context = context
    var contactsList: List<Contact> = emptyList()
        set(newValue) {
            val diffCallback = ContactDiffUtil(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    inner class ContactListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val binding: ContactListItemBinding = ContactListItemBinding.bind(view)
        val contactName = binding.contactName
        val imageView = binding.contactAvatar
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
            CoroutineScope(Dispatchers.Main).launch {
                FragmentViewModel.setContactIndex(position)
                if (DeviceType.isPhone) {
                    navigateToContactInfoFragment(holder.view)
                }
            }
        }
        holder.contactName.setOnLongClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                FragmentViewModel.contactIndex.collect {
                    if (it == position) {
                        FragmentViewModel.setContactIndex(-1)
                    }
                }
            }
            removeContact(item.id)
            showToast()
            true
        }
         holder.imageView.load(item.avatarUrl)
    }

    private fun removeContact(contactId: Int) {
        val newList = ArrayList(contactsList)
        newList.removeIf { it.id == contactId }
        contactsList = newList
        ContactsRepo.contactsList.removeIf { it.id == contactId }
    }

    fun search(query: String) {
        val newList =
            ContactsRepo.contactsList.filter { it.name.lowercase().contains(query.lowercase()) }
        contactsList = newList
    }

    private fun navigateToContactInfoFragment(view: View) {
        val action =
            ContactListFragmentDirections.actionContactListFragmentToContactInfoFragment()
        view.findNavController().navigate(action)
    }
    private fun showToast() {
        Toast.makeText(_context, _context.getString(R.string.delete_contact_message), Toast.LENGTH_LONG).show()
    }
}

