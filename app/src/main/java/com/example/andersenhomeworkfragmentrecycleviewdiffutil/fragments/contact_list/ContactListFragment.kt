package com.example.andersenhomeworkfragmentrecycleviewdiffutil.fragments.contact_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.databinding.FragmentContactListBinding
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.repository.ContactsRepo

class ContactListFragment : Fragment() {
    var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView


//    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.contactItemRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = ContactListAdapter()
        recyclerView.adapter = adapter
        adapter.contactsList = ContactsRepo.contactsList
        binding.searchContact.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null) {
                    adapter.search(p0)
                    recyclerView.scrollToPosition(0)
                }
                return true
            }
        })

    }
}