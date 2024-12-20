package com.example.pam_ucp2.ui.view.dokter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.pam_ucp2.ui.navigation.AlamatNavigasi
import com.example.pam_ucp2.ui.viewmodel.dokter.DktrUIState
import com.example.pam_ucp2.ui.viewmodel.dokter.DokterEvent
import com.example.pam_ucp2.ui.viewmodel.dokter.FormErrorState

object DestinasiInsert : AlamatNavigasi {
    override val route: String = "insert_dktr"
}

@Composable
fun InsertDktr(

){

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
        Text("Jenis Kelamin")
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            spesialis.forEach { sps ->
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = dokterEvent.spesialis == sps,
                        onClick = {
                            onValueChange(dokterEvent.copy(spesialis = sps))
                        }
                    )
                    Text(
                        text = sps
                    )
                }
            }
        }
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