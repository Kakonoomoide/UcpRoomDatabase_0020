package com.example.pam_ucp2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pam_ucp2.data.entity.Dokter
import com.example.pam_ucp2.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

@Dao
interface JadwalDao {
    @Insert
    suspend fun insertJadwal (jadwal: Jadwal)

    @Delete
    suspend fun deleteJadwal (jadwal: Jadwal)

    @Update
    suspend fun updateJadwal (jadwal: Jadwal)

    @Query("SELECT * FROM jadwal ORDER BY idJadwal ASC")
    fun getAllJadwal(): Flow<List<Jadwal>>

    @Query("SELECT * FROM jadwal WHERE idJadwal = :idJadwal")
    fun getJadwal(idJadwal : String): Flow<Jadwal>

    @Query("SELECT * FROM dokter ORDER BY nama DESC")
    fun getAllNamaDokter(): Flow<List<Dokter>>
}