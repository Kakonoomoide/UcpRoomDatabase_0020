package com.example.pam_ucp2.ui.viewmodel.jadwal

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_ucp2.data.entity.Jadwal
import com.example.pam_ucp2.repository.RepositoryJdwl
import com.example.pam_ucp2.ui.navigation.DestinasiDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailJdwlViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryJdwl: RepositoryJdwl,
) : ViewModel(){
    private val _idJadwal: String = checkNotNull(savedStateHandle[DestinasiDetail.idJadwal])

    val detailUiState: StateFlow<DetailUiState> = repositoryJdwl.getJdwl(_idJadwal)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false
            )
        }
        .onStart {
            emit(DetailUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiState (
                isLoading = true,
            ),
        )

    fun deteleMhs() {
        detailUiState.value.detailUiEvent.toJadwalEntity().let {
            viewModelScope.launch {
                repositoryJdwl.deleteJdwl(it)
            }
        }
    }
}

data class DetailUiState(
    val detailUiEvent: JadwalEvent = JadwalEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == JadwalEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != JadwalEvent()
}

// store data from entity to ui
fun Jadwal.toDetailUiEvent() : JadwalEvent {
    return JadwalEvent(
        idJadwal = idJadwal,
        namaDokter = namaDokter,
        namaPasien = namaPasien,
        noHp = noHp,
        tglKonsultasi = tglKonsultasi,
        status = status
    )
}