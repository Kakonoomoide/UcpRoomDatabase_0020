package com.example.pam_ucp2.repository

import com.example.pam_ucp2.data.dao.JadwalDao
import com.example.pam_ucp2.data.entity.Dokter
import com.example.pam_ucp2.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

class LocalRepositoryJdwl(private val jadwalDao: JadwalDao) : RepositoryJdwl {
    override suspend fun insertJdwl(jadwal: Jadwal) {
        jadwalDao.insertJadwal(jadwal)
    }

    override suspend fun deleteJdwl(jadwal: Jadwal) {
        jadwalDao.deleteJadwal(jadwal)
    }

    override suspend fun updateJdwl(jadwal: Jadwal) {
        jadwalDao.updateJadwal(jadwal)
    }

    override fun getAllJdwl(): Flow<List<Jadwal>> {
        return jadwalDao.getAllJadwal()
    }

    override fun getJdwl(idJadwal: String): Flow<Jadwal> {
        return jadwalDao.getJadwal(idJadwal)
    }

    override fun getAllNamaDokter(): Flow<List<Dokter>> {
        return jadwalDao.getAllNamaDokter()
    }
}