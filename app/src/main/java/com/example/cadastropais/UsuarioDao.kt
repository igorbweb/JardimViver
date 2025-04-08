package com.example.cadastropais

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Insert
    suspend fun inserir(usuario: UsuarioEntity)

    @Query("SELECT * FROM usuarios")
    suspend fun listar(): List<UsuarioEntity>
}