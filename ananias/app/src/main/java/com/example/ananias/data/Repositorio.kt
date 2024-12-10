package com.example.salomaofirebase.data

import com.example.ananias.data.Post
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class Repositorio {

    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("ananias")

    fun salvarPost(userId: String, descricao: String, titulo: String) {
        myRef
            .child(userId)
            .push()
            .setValue(Post(descricao = descricao, titulo = titulo))

    }

    fun pegarPosts(userId: String): Flow<List<Post>> {
        return callbackFlow {
            val listener = myRef.child(userId).addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val posts = snapshot.children.mapNotNull {
                        Post(
                            id = it.key !!,
                            descricao = it.child("descricao").value.toString(),
                            titulo = it.child("titulo").value.toString(),
                            data = it.child("data").value.toString()
                        )
                    }
                    trySend(posts)
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })
            awaitClose {
                myRef.removeEventListener(listener)
            }
        }
    }

    suspend fun pegarPostPorId(userId: String, postId: String): Post {
        val doc = myRef.child(userId).child(postId).get().await()
        val document = doc
        val post = Post(
            document.key !!,
            document.child("titulo").value.toString(),
            document.child("descricao").value.toString(),
            document.child("data").value.toString()
        )
        return post
    }
}