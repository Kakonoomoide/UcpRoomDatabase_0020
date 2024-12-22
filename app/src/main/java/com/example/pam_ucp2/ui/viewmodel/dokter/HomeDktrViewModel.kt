package com.example.pam_ucp2.ui.viewmodel.dokter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_pert9.repository.RepositoryDktr
import com.example.pam_ucp2.data.entity.Dokter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeDktrViewModel(
    private val repositoryDktr: RepositoryDktr
): ViewModel() {
    val homeUiState: StateFlow<HomeUiStateDokter> = repositoryDktr.getAllDktr()
        .filterNotNull()
        .map {
            HomeUiStateDokter(
                listDktr = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeUiStateDokter(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUiStateDokter(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiStateDokter(
                isLoading = true,
            )
        )
}

// state; mengubah tampilan
data class HomeUiStateDokter(
    val listDktr: List<Dokter> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)