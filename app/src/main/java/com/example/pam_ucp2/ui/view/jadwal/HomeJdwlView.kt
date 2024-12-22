package com.example.pam_ucp2.ui.view.jadwal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam_ucp2.data.entity.Jadwal
import com.example.pam_ucp2.ui.costumewidget.TopAppBar
import com.example.pam_ucp2.ui.viewmodel.PenyediaViewModel
import com.example.pam_ucp2.ui.viewmodel.jadwal.HomeJadwalViewModel
import com.example.pam_ucp2.ui.viewmodel.jadwal.HomeUiStateJadwal
import kotlinx.coroutines.launch

@Composable
fun HomeJdwlView(
    viewModel: HomeJadwalViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddMhs: () -> Unit = { },
    onDetailClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {
    Scaffold (topBar = {
        TopAppBar(
            judul = "Daftar Jadwal Pasien",
            showBackButton = false,
            onBack = { },
        )
    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddMhs,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Jadwal")
            }
        }){
            innerPadding ->
        val homeUiState by viewModel.HomeUiStateJadwal.collectAsState()

        BodyHomeMhsView(
            homeUiState = homeUiState,
            onClick = {
                onDetailClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeMhsView(
    homeUiState: HomeUiStateJadwal,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() } //Snackbar stste
    when {
        homeUiState.isLoading -> {
            // Menampilkan indikator loading
            Box (modifier= modifier.fillMaxSize(),
                contentAlignment = Alignment.Center)
            {
                CircularProgressIndicator()
            }
        }

        homeUiState.isError -> {
            //menampilkan pesan error
            LaunchedEffect(homeUiState.errorMessage) {
                homeUiState.errorMessage?.let{message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)  //menampilkan snackbar
                    }
                }
            }
        }

        homeUiState.listJdwl.isEmpty() -> {
            //menampilkan pesan jika data kosong
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Tidak ada data mahasiswa.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else -> {
            //Menampilkan daftar mahasiswa
            ListJadwal(
                listJdwl = homeUiState.listJdwl,
                onClick = {
                    onClick(it)
                    println(it)
                },
                modifier = Modifier.padding(top = 100.dp)
            )
        }
    }
}


@Composable
fun ListJadwal(
    listJdwl: List<Jadwal>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
){
    LazyColumn (modifier = Modifier){
        items(
            items = listJdwl,
            itemContent = { jdwl ->
                CardJdwl(
                    jdwl = jdwl,
                    onClick = {onClick(jdwl.idJadwal)}
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardJdwl(
    jdwl: Jadwal,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    Card (
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Column(modifier = Modifier.padding(12.dp)
        )
        {
            Row (modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically)
            {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = jdwl.namaPasien,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Row (modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                verticalAlignment = Alignment.CenterVertically)
            {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = jdwl.namaDokter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row (modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                verticalAlignment = Alignment.CenterVertically)
            {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = jdwl.tglKonsultasi,
                    fontWeight = FontWeight.Bold
                )
            }
            Row (modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                verticalAlignment = Alignment.CenterVertically)
            {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = jdwl.status,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}