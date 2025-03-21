package com.example.p6

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.p6.model.DataJK
import com.example.p6.ui.view.FormulirView
import com.example.p6.ui.view.TampilMahasiswaView
import com.example.p6.ui.viewmodel.MahasiswaViewModel

enum class Halaman {
    Form,
    Data
}

@Composable
fun Navigasi(
    modifier: Modifier = Modifier,
    viewModel: MahasiswaViewModel = viewModel(),
    navHost: NavHostController = rememberNavController()
) {
    Scaffold { paddingValues ->

        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            modifier = modifier.padding(paddingValues),
            navController = navHost,
            startDestination = Halaman.Form.name
        ) {

            composable(route = Halaman.Form.name) {
                val context = LocalContext.current

                FormulirView(
                    pilihanJK = DataJK.isiJK.map { id ->
                        context.resources.getString(id)
                    },
                    onClickButton = { mahasiswaData ->
                        viewModel.saveDataMahasiswa(mahasiswaData)
                        navHost.navigate(Halaman.Data.name)
                    }
                )
            }


            composable(route = Halaman.Data.name) {
                TampilMahasiswaView(
                    mhs = uiState
                )
            }
        }
    }
}
