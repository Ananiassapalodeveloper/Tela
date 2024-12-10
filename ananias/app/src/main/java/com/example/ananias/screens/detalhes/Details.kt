package com.example.ananias.screens.detalhes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salomaofirebase.ui.theme.detalhes.DetalheViewModel
import kotlinx.serialization.Serializable

@Serializable
data class DetailsPage(val id: String)

@Composable
fun DetailsPage(
    viewModel: DetalheViewModel
) {
    val post = viewModel.post.collectAsState().value
    if (post == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        modifier = Modifier,
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Retorna para a página anterior",
                            tint = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))


                Spacer(modifier = Modifier.height(25.dp))


                Text(
                    text = post.titulo,
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Text(
                    text = post.data,
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )


                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = post.descricao,
                    modifier = Modifier,
                    fontSize = 16.sp
                )
            }
        }
    }
}

