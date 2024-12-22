package com.example.pam_ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pam_ucp2.AdminRsApp
import com.example.pam_ucp2.ui.viewmodel.dokter.DokterViewModel
import com.example.pam_ucp2.ui.viewmodel.dokter.HomeDktrViewModel
import com.example.pam_ucp2.ui.viewmodel.jadwal.JadwalViewModel

object PenyediaViewModel{
    val Factory = viewModelFactory {
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
        initializer {
            JadwalViewModel(
                adminRsApp().containerApp.repositoryJdwl
            )
        }
    }
}

fun CreationExtras.adminRsApp(): AdminRsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AdminRsApp)