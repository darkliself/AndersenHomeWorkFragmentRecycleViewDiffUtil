package com.example.andersenhomeworkfragmentrecycleviewdiffutil.fragments.contact_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.databinding.FragmentContactInfoBinding
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.fragments.FragmentViewModel
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.repository.Contact
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.repository.ContactsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactInfoFragment : Fragment() {
    var _binding: FragmentContactInfoBinding? = null
    private val binding get() = _binding!!
    private var contact: Contact? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            FragmentViewModel.contactIndex.collect {
                when (it) {
                    -2 -> {
                        binding.contactInfoFullName.text = ""
                        binding.contactInfoNumber.text = ""
                    }
                    -1 -> {
                        binding.contactInfoFullName.text = ""
                        binding.contactInfoNumber.text = ""
                        binding.contactInfoAvatar.load("")
                        binding.contactInfoAbout.text = ""
                    }
                    else -> {
                        contact = ContactsRepo.getContact(it)
                        binding.contactInfoAvatar.load(contact!!.avatarUrl)
                        binding.contactInfoFullName.text = contact!!.name
                        binding.contactInfoNumber.text = contact!!.number
                        binding.contactInfoAbout.text = contact!!.about
                    }
                }
            }
        }
    }
}