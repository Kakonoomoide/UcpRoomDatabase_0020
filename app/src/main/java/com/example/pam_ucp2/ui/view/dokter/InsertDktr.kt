package com.example.pam_ucp2.ui.view.dokter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam_ucp2.ui.costumewidget.DynamicSelectedField
import com.example.pam_ucp2.ui.costumewidget.TopAppBar
import com.example.pam_ucp2.ui.navigation.AlamatNavigasi
import com.example.pam_ucp2.ui.viewmodel.PenyediaViewModel
import com.example.pam_ucp2.ui.viewmodel.dokter.DktrUIState
import com.example.pam_ucp2.ui.viewmodel.dokter.DokterEvent
import com.example.pam_ucp2.ui.viewmodel.dokter.DokterViewModel
import com.example.pam_ucp2.ui.viewmodel.dokter.FormErrorState
import kotlinx.coroutines.launch

object DestinasiInsert : AlamatNavigasi {
    override val route: String = "insert_dktr"
}

@Composable
fun InsertDktr(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DokterViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
// get uistate and snackbar
    val uiState = viewModel.uiState
    val snacBarHostState = remember{SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // obser on change snack bar
    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                //tampil snackbar
                snacBarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snacBarHostState) }
    ){ padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Dokter"
            )
            // isi body
            InsertBodyDktr (
                uiState = uiState,
                onValueChange = { uppdateEvent ->
                    viewModel.updateState(uppdateEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveData()
                    }
                    onNavigate()
                }
            )
        }
    }
}

@Composable
fun InsertBodyDktr(
    modifier: Modifier = Modifier,
    onValueChange: (DokterEvent) -> Unit,
    uiState: DktrUIState,
    onClick: () -> Unit
){
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FormDokter(
            dokterEvent = uiState.dokterEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }
    }
}

@Composable
fun FormDokter(
    modifier: Modifier = Modifier,
    onValueChange:(DokterEvent)-> Unit,
    dokterEvent: DokterEvent = DokterEvent(),
    errorState: FormErrorState = FormErrorState()
){
    var chosenDropdown by remember {
        mutableStateOf("")
    }

    var listData: MutableList<String> = mutableListOf(chosenDropdown)

    val spesialis = listOf(
        "Dokter spesialis bedah",
        "Dokter spesialis penyakit dalam",
        "Dokter spesialis endokrin",
        "Dokter spesialis anak",
        "Dokter spesialis saraf"
    )

    Column (
        modifier = modifier.fillMaxWidth()
    ){
        // Id dokter
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.idDokter,
            onValueChange = {
                onValueChange(dokterEvent.copy(idDokter = it))
            },
            label = { Text("Id Dokter") },
            isError = errorState.idDokter != null,
            placeholder = { Text("Masukkan Id Dokter") }
        )
        Text(
            text = errorState.idDokter ?: "",
            color = Color.Red
        )
        // Name
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.nama,
            onValueChange = {
                onValueChange(dokterEvent.copy(nama = it))
            },
            label = { Text("Nama") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama") }
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )
        // Spesialis
        DynamicSelectedField(
            value = dokterEvent.spesialis,
            selectedValue = chosenDropdown,
            options = spesialis,
            lable = "Spesialis",
            onValueChangedEvent = {
                onValueChange(dokterEvent.copy(spesialis = it))
            }
        )

        Text(
            text = errorState.spesialis ?: "",
            color = Color.Red
        )

        // klinik
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.klinik,
            onValueChange = {
                onValueChange(dokterEvent.copy(klinik = it))
            },
            label = { Text("Klinik") },
            isError = errorState.klinik != null,
            placeholder = { Text("Masukkan Klinik") }
        )
        Text(
            text = errorState.klinik ?: "",
            color = Color.Red
        )

        // no HP
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.noHp,
            onValueChange = {
                onValueChange(dokterEvent.copy(noHp = it))
            },
            label = { Text("No Telepon") },
            isError = errorState.noHp != null,
            placeholder = { Text("Masukkan No Telepon") }
        )
        Text(
            text = errorState.noHp ?: "",
            color = Color.Red
        )

        // Jam kerja
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.jamKerja,
            onValueChange = {
                onValueChange(dokterEvent.copy(jamKerja = it))
            },
            label = { Text("Jam Kerja") },
            isError = errorState.jamKerja != null,
            placeholder = { Text("Masukkan Jam Kerja") }
        )
        Text(
            text = errorState.jamKerja ?: "",
            color = Color.Red
        )
    }
}