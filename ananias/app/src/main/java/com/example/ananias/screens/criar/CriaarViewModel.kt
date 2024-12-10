package com.example.ananias.screens.criar

import androidx.lifecycle.ViewModel
import com.example.salomaofirebase.data.Repositorio
import com.google.firebase.auth.FirebaseAuth

class CriaarViewModel: ViewModel() {
    private val repositorio = Repositorio()
    private val  userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""


    fun criarPost(descricao: String, titulo: String) {
        repositorio.salvarPost(userId, descricao, titulo)
    }
}