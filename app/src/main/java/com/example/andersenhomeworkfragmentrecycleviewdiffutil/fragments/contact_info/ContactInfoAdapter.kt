package com.example.andersenhomeworkfragmentrecycleviewdiffutil.fragments.contact_info

//class ContactInfoAdapter(private val index: String): RecyclerView.Adapter<ContactInfoAdapter.ContactInfoViewHolder>() {
//
//    private val filteredWords: Contact
//
//    init {
//        Log.d("INFO_TEST",  "$index")
//        filteredWords = ContactsRepo.getContact(index.toInt())
//    }
//
//    class ContactInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val contactName = view.findViewById<TextView>(R.id.contact_info_full_name)
//        val image = view.findViewById<ImageView>(R.id.contact_info_avatar)
//        val number = view.findViewById<TextView>(R.id.contact_info_number)
//        val info = view.findViewById<TextView>(R.id.contact_info_about)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactInfoViewHolder {
//        val layout = LayoutInflater
//            .from(parent.context)
//            .inflate(R.layout.contact_info_item, parent, false)
//        layout.accessibilityDelegate = View.AccessibilityDelegate()
//        return ContactInfoViewHolder(layout)
//    }
//
//    override fun getItemCount(): Int {
//        return 1
//    }
//
//    override fun onBindViewHolder(holder: ContactInfoViewHolder, position: Int) {
//        val item = filteredWords
//        holder.contactName.text = "${item.name} ${item.surname}"
//        holder.image.setImageURI(item.avatarUrl.toUri())
//        holder.info.text = item.about
//        holder.number.text = item.number
//
////        holder.layout.setOnClickListener {
////            val action = ContactListFragmentDirections.actionContactListFragmentToContactInfoFragment()
////            holder.view.findNavController().navigate(action)
////        }
//    }
//
//}

