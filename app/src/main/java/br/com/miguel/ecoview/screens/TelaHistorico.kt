package br.com.miguel.ecoview.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.miguel.ecoview.R
import br.com.miguel.ecoview.ui.theme.EcoViewTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TelaHistorico() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                MyTopAppBar()
            },
            bottomBar = {
                MyBottomAppBar()
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {},
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
        ) {


            /*ContentScreen(
                modifier = Modifier.padding(paddingValues),
                navController
            )*/

        }

    }
}

@Preview
@Composable
private fun TelaPrincipalPreview() {
    EcoViewTheme {
        TelaHistorico()
    }
}