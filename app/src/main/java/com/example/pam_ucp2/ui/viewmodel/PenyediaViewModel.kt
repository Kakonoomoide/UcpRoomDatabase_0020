package com.example.pam_ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pam_ucp2.AdminRsApp

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            DokterViewModel(
                adminRsApp().containerApp.repositoryDktr
            )
        }
    }
}

fun CreationExtras.adminRsApp(): AdminRsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AdminRsApp)