package com.example.pam_ucp2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pam_ucp2.data.entity.Dokter
import kotlinx.coroutines.flow.Flow


@Dao
interface DokterDao {
    @Insert
    suspend fun insertDokter(dokter: Dokter)

    @Query("SELECT * FROM dokter ORDER BY nama ASC")
    fun getAllDokter(): Flow<List<Dokter>>

    @Query("SELECT * FROM dokter WHERE idDokter = :idDokter")
    fun getDokter(idDokter : String): Flow<Dokter>
}