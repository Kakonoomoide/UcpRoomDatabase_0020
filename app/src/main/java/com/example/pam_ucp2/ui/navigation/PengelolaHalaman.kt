package com.example.pam_ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pam_ucp2.ui.view.HomePage
import com.example.pam_ucp2.ui.view.dokter.HomeDktrView
import com.example.pam_ucp2.ui.view.dokter.InsertDktr
import com.example.pam_ucp2.ui.view.jadwal.DetailJdwlView
import com.example.pam_ucp2.ui.view.jadwal.HomeJdwlView
import com.example.pam_ucp2.ui.view.jadwal.InsertJdwlView
import com.example.pam_ucp2.ui.view.jadwal.UpdateJdwlView

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
                    navController.navigate(DestinationDokterHome.route)
                },
                jadwalPageButton = {
                    navController.navigate(DestinationJadwalHome.route)
                },
            )
        }

        // Home Dokter
        composable(
            route = DestinationDokterHome.route
        ){
            HomeDktrView(
                onAddDktr ={
                    navController.navigate(DestinationDokterInsert.route)
                },
                onBack = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        // Insert Dokter
        composable(
            route = DestinationDokterInsert.route
        ){
            InsertDktr(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                }
            )
        }
        // Home Jadwal
        composable(
            route = DestinationJadwalHome.route
        ){
            HomeJdwlView(
                onDetailClick = { idjadwal ->
                    navController.navigate("${DestinasiDetail.route}/$idjadwal")
                    println("Pengelola Halaman: idJadwal = $idjadwal")
                },
                onAddJdwl ={
                    navController.navigate(DestinationJadwalInsert.route)
                },
                modifier = modifier
            )
        }

        // Insert Jadwal
        composable(
            route = DestinationJadwalInsert.route
        ){
            InsertJdwlView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                }
            )
        }

        // Detail Jadwal
        composable(
            DestinasiDetail.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.idJadwal){
                    type = NavType.StringType
                }
            )
        ){
            val idjadwal = it.arguments?.getString(DestinasiDetail.idJadwal)
            idjadwal?.let { idjadwal ->
                DetailJdwlView(
                    onBack = {
                        navController.popBackStack()
                    },
                    modifier = modifier,
                    onEditClick = {
                        navController.navigate("${DestinasiUpdate.route}/$it")
                    },
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        // Edit Jadwal
        composable(
            DestinasiUpdate.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.idJadwal){
                    type = NavType.StringType
                }
            )
        ){
            UpdateJdwlView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
    }
}