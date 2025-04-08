package com.example.cadastropais

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun FormularioUsuario(dao: UsuarioDao) {
    val context = LocalContext.current

    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = sobrenome,
            onValueChange = { sobrenome = it },
            label = { Text("Sobrenome") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = idade,
            onValueChange = { idade = it.filter { char -> char.isDigit() } },
            label = { Text("Idade") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = endereco,
            onValueChange = { endereco = it },
            label = { Text("Endereço") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (nome.isBlank() || sobrenome.isBlank() || email.isBlank() || idade.isBlank() || endereco.isBlank()) {
                Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                val usuario = UsuarioEntity(
                    nome = nome,
                    sobrenome = sobrenome,
                    email = email,
                    idade = idade.toInt(),
                    endereco = endereco
                )

                CoroutineScope(Dispatchers.IO).launch {
                    dao.inserir(usuario)
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(context, "Usuário salvo!", Toast.LENGTH_SHORT).show()
                    }
                }

                // Limpa os campos
                nome = ""
                sobrenome = ""
                email = ""
                idade = ""
                endereco = ""
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Salvar")
        }
    }
}