package com.example.cadastropais

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cadastropais.ui.theme.CadastroPaisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getInstance(applicationContext)
        val dao = db.usuarioDao()

        setContent {
            CadastroPaisTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    var telaAtual by remember { mutableStateOf("formulario") }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        // Logo
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(bottom = 24.dp)
                                .size(120.dp)
                        )

                        when (telaAtual) {
                            "formulario" -> {
                                FormularioUsuario(dao)

                                Spacer(modifier = Modifier.weight(1f))

                                // Texto para acessar a listagem
                                TextButton(
                                    onClick = { telaAtual = "lista" },
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                ) {
                                    Text(
                                        text = "Ver pais cadastrados",
                                        fontSize = 14.sp,
                                        textDecoration = TextDecoration.Underline
                                    )
                                }
                            }

                            "lista" -> {
                                ListaUsuarios(dao)

                                Spacer(modifier = Modifier.height(16.dp))

                                // Botão para voltar ao formulário
                                Button(
                                    onClick = { telaAtual = "formulario" },
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                ) {
                                    Text("Voltar ao cadastro")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}