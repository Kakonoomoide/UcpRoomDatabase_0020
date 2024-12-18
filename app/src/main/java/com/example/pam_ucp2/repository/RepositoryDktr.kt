package com.example.pam_pert9.repository

import com.example.pam_ucp2.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

interface RepositoryDktr {
    suspend fun insertDktr(dokter: Dokter)

    fun getAllDktr(): Flow<List<Dokter>>

    fun getDktr(idDktr: String): Flow<Dokter>
}