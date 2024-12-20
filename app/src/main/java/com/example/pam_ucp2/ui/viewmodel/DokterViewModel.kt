package com.example.pam_ucp2.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pam_pert9.repository.RepositoryDktr
import com.example.pam_ucp2.data.entity.Dokter

class DokterViewModel (
    private val repositoryDktr: RepositoryDktr
): ViewModel(){

}


// tampung data
data class DokterEvent(
    val idDokter: String = "",
    val nama: String = "",
    val spesialis: String = "",
    val klinik: String = "",
    val noHp: String = "",
    val jamKerja: String = "",
)

fun DokterEvent.toDokterEntity():Dokter = Dokter(
    idDokter = idDokter,
    nama = nama,
    spesialis = spesialis,
    klinik = klinik,
    noHp = noHp,
    jamKerja = jamKerja
)