package com.example.pam_ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pam_ucp2.ui.view.HomePage
import com.example.pam_ucp2.ui.view.dokter.InsertDktr
import com.example.pam_ucp2.ui.view.jadwal.HomeJdwlView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = DestinationHome.route
    ) {
        // Home Page
        composable(
            route = DestinationHome.route
        ){
            HomePage(
                dokterPageButton = {
                    navController.navigate(DestinationDokterInsert.route)
                },
                jadwalPageButton = {
                    navController.navigate(DestinationJadwalInsert.route)
                },
            )
        }
        // Insert Dokter


        // Insert Jadwal
    }
}