package com.example.pam_pert9.repository

import com.example.pam_ucp2.data.dao.DokterDao
import com.example.pam_ucp2.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

class LocalRepositoryDktr(private val dokterDao:DokterDao) : RepositoryDktr {
    override suspend fun insertDktr(dokter: Dokter) {
        dokterDao.insertDokter(dokter)
    }

    override fun getAllDktr(): Flow<List<Dokter>> {
        return dokterDao.getAllDokter()
    }

    override fun getDktr(idDktr: String): Flow<Dokter> {
        return dokterDao.getDokter(idDktr)
    }

}