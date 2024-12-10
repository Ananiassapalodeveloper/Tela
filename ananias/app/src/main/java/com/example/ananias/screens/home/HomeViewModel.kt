package com.example.ananias.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salomaofirebase.data.Repositorio
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class HomeViewModel: ViewModel() {
    private val respositorio = Repositorio()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    val posts = respositorio.pegarPosts(userId)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

}