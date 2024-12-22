package com.example.pam_ucp2.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinationHome : AlamatNavigasi{
    override val route = "home"
}
// destinaton Dokter
object DestinationDokterHome : AlamatNavigasi{
    override val route = "home_dokter"
}

object DestinationDokterInsert : AlamatNavigasi{
    override val route = "insert_dokter"
}

// destinaton Jadwal
object DestinationJadwalInsert : AlamatNavigasi{
    override val route = "insert_jadwal"
}
object DestinationJadwalHome : AlamatNavigasi{
    override val route = "home_jadwal"
}

object DestinasiDetail : AlamatNavigasi {
    override val route = "detail_jadwal"
    const val idJadwal = "idJadwal"
    val routeWithArg = "$route/{$idJadwal}"
}

object DestinasiUpdate : AlamatNavigasi {
    override val route = "update_jadwal"
    const val idJadwal = "idJadwal"
    val routeWithArg = "$route/{$idJadwal}"
}