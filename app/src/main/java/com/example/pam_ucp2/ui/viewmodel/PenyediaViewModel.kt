package com.example.pam_ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pam_ucp2.AdminRsApp
import com.example.pam_ucp2.ui.viewmodel.dokter.DokterViewModel
import com.example.pam_ucp2.ui.viewmodel.dokter.HomeDktrViewModel
import com.example.pam_ucp2.ui.viewmodel.jadwal.DetailJadwalViewModel
import com.example.pam_ucp2.ui.viewmodel.jadwal.HomeJadwalViewModel
import com.example.pam_ucp2.ui.viewmodel.jadwal.JadwalViewModel
import com.example.pam_ucp2.ui.viewmodel.jadwal.UpdateJadwalViewModel

object PenyediaViewModel{
    val Factory = viewModelFactory {
        // view model Dokter
        initializer {
            DokterViewModel(
                adminRsApp().containerApp.repositoryDktr
            )
        }
        initializer {
            HomeDktrViewModel(
                adminRsApp().containerApp.repositoryDktr
            )
        }
        // Jadwal View Model
        initializer {
            JadwalViewModel(
                adminRsApp().containerApp.repositoryJdwl
            )
        }
        initializer {
            HomeJadwalViewModel(
                adminRsApp().containerApp.repositoryJdwl
            )
        }
        initializer {
            DetailJadwalViewModel(
                createSavedStateHandle(),
                adminRsApp().containerApp.repositoryJdwl
            )
        }
        initializer {
            UpdateJadwalViewModel(
                createSavedStateHandle(),
                adminRsApp().containerApp.repositoryJdwl
            )
        }

    }
}

fun CreationExtras.adminRsApp(): AdminRsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AdminRsApp)