package com.example.pam_ucp2

import android.app.Application
import com.example.pam_ucp2.dependeciesinjection.ContainerApp

class AdminRsApp : Application() {
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}