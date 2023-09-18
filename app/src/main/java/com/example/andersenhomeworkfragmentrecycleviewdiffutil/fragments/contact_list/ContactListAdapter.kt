package com.example.andersenhomeworkfragmentrecycleviewdiffutil.fragments.contact_list

import android.app.AlertDialog
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
            removeContactDialog(item.id, position).show()
            true
        }
        holder.imageView.load(item.avatarUrl)
    }

    fun search(query: String) {
        val newList =
            ContactsRepo.contactsList.filter { it.name.lowercase().contains(query.lowercase()) }
        contactsList = newList
    }

    private fun removeContact(contactId: Int) {
        val newList = ArrayList(contactsList)
        newList.removeIf { it.id == contactId }
        contactsList = newList
        ContactsRepo.contactsList.removeIf { it.id == contactId }
    }

    private fun navigateToContactInfoFragment(view: View) {
        val action =
            ContactListFragmentDirections.actionContactListFragmentToContactInfoFragment()
        view.findNavController().navigate(action)
    }

    private fun showToast(message: String) {
        Toast.makeText(
            _context,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun removeContactDialog(contactId: Int, position: Int): AlertDialog {
        val builder = AlertDialog.Builder(_context)
        builder.setMessage(R.string.dialog_message_question)
        builder.setPositiveButton(
            R.string.dialog_message_yes
        ) { _, _ ->
            CoroutineScope(Dispatchers.Main).launch {
                FragmentViewModel.contactIndex.collect {
                    if (it == position) {
                        FragmentViewModel.setContactIndex(-1)
                    }
                }
            }
            removeContact(contactId)
            showToast(_context.getString(R.string.dialog_toast_delete_message))
        }
        builder.setNegativeButton(
            R.string.dialog_message_no
        ) { _, _ -> showToast(_context.getString(R.string.dialog_toast_cancel_message)) }
        return builder.create()
    }
}

