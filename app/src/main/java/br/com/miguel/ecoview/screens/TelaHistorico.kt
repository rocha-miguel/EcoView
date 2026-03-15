package br.com.miguel.ecoview.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.miguel.ecoview.R
import br.com.miguel.ecoview.model.HistoricoCO2
import br.com.miguel.ecoview.navigation.Destination
import br.com.miguel.ecoview.repository.RoomHistoricoCO2Repository
import br.com.miguel.ecoview.repository.RoomUsuarioRepository
import br.com.miguel.ecoview.repository.UsuarioRepository
import br.com.miguel.ecoview.ui.theme.EcoViewTheme


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TelaHistorico(navController: NavHostController, usuarioEmail: String?) {

    var showBottomSheet by remember { mutableStateOf(false) }
    var resultadoCO2 by remember { mutableStateOf<Double?>(null) }

    var historicoRepository = RoomHistoricoCO2Repository(LocalContext.current)
    var usuarioRepository = RoomUsuarioRepository(LocalContext.current)

    var usuario = usuarioRepository.getUserByEmail(usuarioEmail!!)

    var historicoList = historicoRepository.getHistoricoByUsuarioId(usuario!!.id)

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

                ContentScreenHist(historicoList, navController, usuarioEmail)

            }

            ModalCalculo(
                showBottomSheet = showBottomSheet,
                onDismiss = { showBottomSheet = false },
                onCalcular = { resultado ->
                    resultadoCO2 = resultado
                    navController.navigate(Destination.Historico.createRoute(usuarioEmail))
                },
                usuarioEmail
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
fun ContentScreenHist(
    historicoList: List<HistoricoCO2>,
    navController: NavController,
    email: String?
) {

    var historicoRepository = RoomHistoricoCO2Repository(LocalContext.current)

    if (historicoList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.nenhum_c_lculo_salvo_ainda),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            items(historicoList) { item ->
                CardHistorico(item,
                    onExcluir = { historico ->
                        historicoRepository.deleteHistorico(historico)
                    },
                    navController, email)
            }
        }
    }
}

@Composable
fun CardHistorico(
    item: HistoricoCO2,
    onExcluir: (HistoricoCO2) -> Unit,
    navController: NavController,
    email: String?
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.onPrimary,
                                MaterialTheme.colorScheme.secondary
                            )
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .size(height = 137.dp, width = 358.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.impacto_0f_kg_de_co).format(item.resultadoCo2),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                    )

                    Text(
                        text = stringResource(R.string.km_rodados).format(item.kmRodados),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .align(Alignment.Start)
                    )

                    Text(
                        text = stringResource(R.string.alimenta_oo).format(item.alimentacao),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .align(Alignment.Start)
                    )

                    Text(
                        text = stringResource(R.string.consumo_de_energiaa).format(item.consumoEnergia),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .align(Alignment.Start)
                    )
                }

                IconButton(
                    onClick = {
                        onExcluir(item)
                        navController.navigate(Destination.Historico.createRoute(email!!))},
                    modifier = Modifier.align(Alignment.BottomEnd),

                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Excluir cálculo",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}