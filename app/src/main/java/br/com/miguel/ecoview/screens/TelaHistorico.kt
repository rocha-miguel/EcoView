package br.com.miguel.ecoview.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.miguel.ecoview.R
import br.com.miguel.ecoview.ui.theme.EcoViewTheme


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TelaHistorico(navController: NavHostController, usuarioEmail: String?) {

    var showBottomSheet by remember { mutableStateOf(false) }
    var resultadoCO2 by remember { mutableStateOf<Double?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                MyTopAppBar(navController, usuarioEmail)
            },
            bottomBar = {
                MyBottomAppBar(navController, usuarioEmail)
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        showBottomSheet = true
                    },
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.realizar_novo_c_lculo),
                        tint = MaterialTheme.colorScheme.surface
                    )
                }
            }
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                resultadoCO2?.let {
                    Text(
                        text = "Resultado: %.2f kg de CO₂".format(it)
                    )
                }
            }

            ModalCalculo(
                showBottomSheet = showBottomSheet,
                onDismiss = { showBottomSheet = false },
                onCalcular = { resultado ->
                    resultadoCO2 = resultado
                }
            )
        }
    }
}




@Preview
@Composable
private fun TelaPrincipalPreview() {
    EcoViewTheme {
        TelaHistorico(rememberNavController(), "")
    }
}

@Composable
fun ContentScreenHist(modifier: Modifier = Modifier) {

    LazyColumn() { }
    
}