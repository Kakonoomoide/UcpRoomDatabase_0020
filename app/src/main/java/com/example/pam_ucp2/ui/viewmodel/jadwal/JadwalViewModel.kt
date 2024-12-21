package com.example.pam_ucp2.ui.viewmodel.jadwal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_ucp2.data.entity.Jadwal
import com.example.pam_ucp2.repository.RepositoryJdwl
import kotlinx.coroutines.launch

class JadwalViewModel(
    private val repositoryJdwl: RepositoryJdwl
): ViewModel(){
    var uiState by mutableStateOf(JdwlUIState())

    // update state berdasarkan input
    fun updateState(jadwalEvent: JadwalEvent){
        uiState = uiState.copy(
            jadwalEvent = jadwalEvent,
        )
    }

    //validasi
    private fun validateFields(): Boolean{
        val event = uiState.jadwalEvent
        val errorState = FormErrorState(
            idJadwal = if (event.idJadwal.isNotEmpty()) null else "ID tidak boleh kosong!!",
            namaDokter = if (event.namaDokter.isNotEmpty()) null else "Nama Dokter tidak boleh kosong!!",
            namaPasien = if (event.namaPasien.isNotEmpty()) null else "Nama Pasien boleh kosong!!",
            noHp = if (event.noHp.isNotEmpty()) null else "No Telpon tidak boleh kosong!!",
            tglKonsultasi = if (event.tglKonsultasi.isNotEmpty()) null else "Tanggal Konsultasi tidak boleh kosong!!",
            status = if (event.status.isNotEmpty()) null else "Status tidak boleh kosong!!",
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    //save data
    fun saveData(){
        val currentEvent = uiState.jadwalEvent

        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryJdwl.insertJdwl(currentEvent.toJadwalEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        //reset input and error state
                        jadwalEvent = JadwalEvent(),
                        isEntryValid = FormErrorState(),
                    )
                }
                catch (e: Exception){
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        }
        else{
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data anda."
            )
        }
    }

    //reset message snackbar
    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}

// UI State
data class JdwlUIState(
    val jadwalEvent: JadwalEvent = JadwalEvent(),
    val isEntryValid: FormErrorState= FormErrorState(),
    val snackBarMessage: String? = null
)

// validation
data class FormErrorState(
    val idJadwal: String? = null,
    val namaDokter: String? = null,
    val namaPasien: String? = null,
    val noHp: String? = null,
    val tglKonsultasi: String? = null,
    val status: String? = null,
){
    fun isValid(): Boolean{
        return idJadwal == null
                &&  namaDokter === null
                &&  namaPasien === null
                &&  noHp === null
                &&  tglKonsultasi === null
                &&  status === null
    }
}

// store data form input
data class JadwalEvent(
    val idJadwal: String = "",
    val namaDokter: String = "",
    val namaPasien: String = "",
    val noHp: String = "",
    val tglKonsultasi: String = "",
    val status: String = "",
)
fun JadwalEvent.toJadwalEntity():Jadwal = Jadwal(
    idJadwal = idJadwal,
    namaDokter = namaDokter,
    namaPasien = namaPasien,
    noHp = noHp,
    tglKonsultasi = tglKonsultasi,
    status = status
)