package com.example.pam_ucp2.repository

import com.example.pam_ucp2.data.entity.Dokter
import com.example.pam_ucp2.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

interface RepositoryJdwl {
    suspend fun insertJdwl(jadwal: Jadwal)

    suspend fun deleteJdwl(jadwal: Jadwal)

    suspend fun updateJdwl(jadwal: Jadwal)

    fun getAllJdwl(): Flow<List<Jadwal>>

    fun getJdwl(idJadwal: String): Flow<Jadwal>

    fun getAllNamaDokter(): Flow<List<Dokter>>
}