package com.example.andersenhomeworkfragmentrecycleviewdiffutil.fragments.contact_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.R
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.databinding.FragmentContactInfoBinding
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.repository.Contact
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.repository.ContactsRepo


private const val CONTACT_INDEX_KEY = "contact_index"

class ContactInfoFragment : Fragment() {
    var _binding: FragmentContactInfoBinding? = null
    private val binding get() = _binding!!
    private var contact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contact = ContactsRepo.getContact(it.getString(CONTACT_INDEX_KEY)!!.toInt())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve and inflate the layout for this fragment
        _binding = FragmentContactInfoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contact?.let {
            binding.contactInfoFullName.text = contact!!.name
            binding.contactInfoNumber.text = contact!!.number
            binding.contactInfoAbout.text = contact!!.about + contact!!.about
//            binding.contactInfoAvatar.setImageURI()
        } ?: run {
            binding.contactInfoAbout.text = getString(R.string.tablet_contact_hint)
        }
    }
}