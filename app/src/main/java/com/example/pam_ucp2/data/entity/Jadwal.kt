package com.example.pam_ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jadwal")
data class Jadwal(
    @PrimaryKey
    val idJadwal: String,
    val namaDokter: String,
    val namaPasien: String,
    val noHp: String,
    val tglKonsultasi: String,
    val status: String,
)