package com.example.cadastropais

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val sobrenome: String,
    val email: String,
    val idade: Int,
    val endereco: String
)
