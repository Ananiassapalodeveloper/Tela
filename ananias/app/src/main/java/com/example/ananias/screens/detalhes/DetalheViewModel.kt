package com.example.salomaofirebase.ui.theme.detalhes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ananias.data.Post
import com.example.salomaofirebase.data.Repositorio
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetalheViewModel(
    private val postId: String
) : ViewModel() {
    private val repositorio = Repositorio()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    var post = MutableStateFlow<Post?>(null)
        private set

    init {
        viewModelScope.launch {
            post.value = repositorio.pegarPostPorId(userId, postId)
        }
    }
}