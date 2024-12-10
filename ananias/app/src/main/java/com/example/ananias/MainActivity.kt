package com.example.ananias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ananias.screens.cadastro.CadastrarPage
import com.example.ananias.screens.criar.CreatePage
import com.example.ananias.screens.detalhes.DetailsPage
import com.example.ananias.screens.home.HomePage
import com.example.ananias.screens.login.LoginPage
import com.example.ananias.ui.theme.AnaniasTheme
import com.example.salomaofirebase.ui.theme.detalhes.DetalheViewModel
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnaniasTheme {
                val navController = rememberNavController()
                val user = FirebaseAuth.getInstance().currentUser
                val startDestination = if (user != null) HomePage else LoginPage
                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {
                    composable<CadastrarPage> {
                        CadastrarPage(
                            voltar = { navController.navigate(LoginPage) }
                        )
                    }
                    composable<CreatePage> {
                        CreatePage()
                    }
                    composable<DetailsPage> {
                        val postId = it.toRoute<DetailsPage>().id
                        DetailsPage(DetalheViewModel(postId))
                    }
                    composable<HomePage> {
                        HomePage(
                            criarPost = { navController.navigate(CreatePage) },
                            detalhes = { id -> navController.navigate(DetailsPage(id)) }
                        )
                    }
                    composable<LoginPage> {
                        LoginPage(
                            login = { navController.navigate(HomePage) },
                            cadastrar = { navController.navigate(CadastrarPage) }
                        )
                    }
                }
            }
        }
    }
}

