package com.example.pam_ucp2.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinationHome : AlamatNavigasi{
    override val route = "home"
}

object DestinationDokterInsert : AlamatNavigasi{
    override val route = "insert_dokter"
}

object DestinationJadwalInsert : AlamatNavigasi{
    override val route = "insert_jadwal"
}