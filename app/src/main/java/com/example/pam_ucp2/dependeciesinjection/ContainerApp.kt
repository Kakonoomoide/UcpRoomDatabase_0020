package com.example.pam_ucp2.dependeciesinjection

import android.content.Context
import com.example.pam_pert9.repository.LocalRepositoryDktr
import com.example.pam_pert9.repository.RepositoryDktr
import com.example.pam_ucp2.data.database.RsDatabase
import com.example.pam_ucp2.repository.LocalRepositoryJdwl
import com.example.pam_ucp2.repository.RepositoryJdwl

interface InterfaceContainerApp {
    val repositoryJdwl: RepositoryJdwl
    val repositoryDktr: RepositoryDktr
}

class ContainerApp(private val context: Context) : InterfaceContainerApp{
    override val repositoryDktr: RepositoryDktr by lazy {
        LocalRepositoryDktr(RsDatabase.getDatabase(context).DokterDao())
    }
    override val repositoryJdwl: RepositoryJdwl by lazy {
        LocalRepositoryJdwl(RsDatabase.getDatabase(context).JadwalDao())
    }
}