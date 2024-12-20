package com.example.pam_ucp2.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pam_pert9.repository.RepositoryDktr
import com.example.pam_ucp2.data.entity.Dokter

class DokterViewModel (
    private val repositoryDktr: RepositoryDktr
): ViewModel(){

}

// ui state

// validation
data class  FormErrorState(
    val idDokter: String? = null,
    val nama: String? = null,
    val spesialis: String? = null,
    val klinik: String? = null,
    val noHp: String? = null,
    val jamKerja: String? = null,
){
    fun isValid(): Boolean{
        return idDokter == null
                && nama == null
                && spesialis == null
                && klinik == null
                && noHp == null
                && jamKerja == null
    }
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