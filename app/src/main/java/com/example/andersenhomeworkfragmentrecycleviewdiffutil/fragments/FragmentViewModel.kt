package com.example.andersenhomeworkfragmentrecycleviewdiffutil.fragments

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FragmentViewModel : ViewModel() {
    companion object {
        private val _contactIndex = MutableStateFlow(-2)
        val contactIndex: Flow<Int> = _contactIndex

        suspend fun setContactIndex(newValue: Int) {
            _contactIndex.emit(newValue)
        }
    }
}