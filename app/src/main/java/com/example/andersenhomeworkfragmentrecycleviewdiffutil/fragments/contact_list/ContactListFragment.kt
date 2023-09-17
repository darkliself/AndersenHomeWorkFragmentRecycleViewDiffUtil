package com.example.andersenhomeworkfragmentrecycleviewdiffutil.fragments.contact_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.R
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.databinding.FragmentContactListBinding
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.repository.ContactsRepo

class ContactListFragment : Fragment() {
    var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.contactItemRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = ContactListAdapter(this.requireContext())
        recyclerView.adapter = adapter
        adapter.contactsList = ContactsRepo.contactsList
        addPaddingsToRecycleView(
            resources.getInteger(R.integer.side_padding),
            resources.getInteger(R.integer.top_padding)
        )
        addDividerToRecycleVie(requireContext())
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

    private fun addPaddingsToRecycleView(sidePadding: Int, topPadding: Int) {
        val paddings = RecyclerDecoration(
            sidePadding,
            topPadding
        )
        recyclerView.addItemDecoration(paddings)
    }

    private fun addDividerToRecycleVie(context: Context) {
        val divider = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)?.let {
            divider.setDrawable(it)
        }
        recyclerView.addItemDecoration(divider)
    }
}