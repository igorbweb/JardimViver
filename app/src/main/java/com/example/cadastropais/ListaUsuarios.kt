package com.example.cadastropais

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ListaUsuarios(dao: UsuarioDao) {
    var usuarios by remember { mutableStateOf<List<UsuarioEntity>>(emptyList()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(true) {
        scope.launch(Dispatchers.IO) {
            val resultado = dao.listar()
            usuarios = resultado
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Usuários Cadastrados", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(usuarios) { usuario ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Nome: ${usuario.nome} ${usuario.sobrenome}")
                        Text("Email: ${usuario.email}")
                        Text("Idade: ${usuario.idade}")
                        Text("Endereço: ${usuario.endereco}")
                    }
                }
            }
        }
    }
}