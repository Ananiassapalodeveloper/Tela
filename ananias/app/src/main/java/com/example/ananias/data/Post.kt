package com.example.ananias.data

import java.time.LocalDate

data class Post(
    val id: String = "",
    val titulo: String,
    val descricao: String,
    val data: String = LocalDate.now().toString()
)
