package br.com.miguel.ecoview.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.miguel.ecoview.R
import br.com.miguel.ecoview.navigation.Destination
import br.com.miguel.ecoview.ui.theme.EcoViewTheme


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TelaPrincipal(navController: NavHostController) {

    var showBottomSheet by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                MyTopAppBar(navController)
            },
            bottomBar = {
                MyBottomAppBar(navController)
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
        ) {


            /*ContentScreen(
                modifier = Modifier.padding(paddingValues),
                navController
            )*/

        }

        ModalCalculo(
            showBottomSheet = showBottomSheet,
            onDismiss = { showBottomSheet = false }
        )


    }
}


@Preview
@Composable
private fun TelaPrincipalPreview() {
    EcoViewTheme {
        TelaPrincipal(rememberNavController())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(navController: NavController) {
    /*//val userRepository: UserRepository = SharedPreferencesUserRepository(LocalContext.current)
    val userRepository: UserRepository = RoomUserRepository(LocalContext.current)

    val user = userRepository.getUserByEmail(email)*/

    /*var profileBitmap by remember {
        mutableStateOf<Bitmap>(
            convertByteArrayToBitmap(
                imageArray = TODO()
            )
        )
    }*/

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        //.height(60.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,

            ),


        title = {
            Row(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Column {
                    Text(
                        text = "Olá, ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold

                    )
                    Text(
                        text = "",
                        style = MaterialTheme.typography.displaySmall
                    )
                }

                Card(
                    modifier = Modifier
                        .size(48.dp)
                        .clickable(
                            onClick = {
                                navController.navigate(Destination.Atualizar.route)

                            }
                        ),
                    shape = CircleShape,
                    colors = CardDefaults
                        .cardColors(
                            containerColor = Color.Transparent
                        ),
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Image(
                        painter = painterResource(R.drawable.light_bulb),
                        //bitmap = profileBitmap.asImageBitmap(),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        contentDescription = stringResource(R.string.imagem_usu_rio)
                    )
                }

            }
        }
    )
}

@Preview
@Composable
private fun MyTopAppBarPreview() {
    EcoViewTheme {
        MyTopAppBar(rememberNavController())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalCalculo(
    showBottomSheet: Boolean,
    onDismiss: () -> Unit
) {
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            containerColor = Color.Transparent
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(32.dp),
                CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )

            ) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(32.dp))
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.onPrimary,
                                    MaterialTheme.colorScheme.primary
                                )
                            )
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp),
                        text = stringResource(R.string.emiss_o_de_co),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.surface

                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    var km by remember {
                        mutableStateOf("")
                    }

                    var energia by remember {
                        mutableStateOf("")
                    }



                    OutlinedTextField(
                        value = km,
                        singleLine = true,
                        onValueChange = {
                            km = it
                        },
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                            .fillMaxWidth(),
                        label = {
                            Text(
                                text = stringResource(R.string.quantos_km_dirigiu_hoje),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.surface
                            )
                        },
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.surface,
                            unfocusedBorderColor = MaterialTheme.colorScheme.surface,
                            focusedTextColor = MaterialTheme.colorScheme.surface,
                            unfocusedTextColor = MaterialTheme.colorScheme.surface
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Speed,
                                contentDescription = stringResource(R.string.quilometragem),
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )

                    var expandedTipo by remember { mutableStateOf(false) }
                    var tipoSelecionado by remember { mutableStateOf("") }

                    ExposedDropdownMenuBox(
                        expanded = expandedTipo,
                        onExpandedChange = { expandedTipo = !expandedTipo }
                    ) {
                        OutlinedTextField(
                            value = tipoSelecionado,
                            onValueChange = {},
                            readOnly = true,
                            label = {
                                Text(
                                    text = "Alimentação",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.surface
                                )
                            },
                            placeholder = {
                                Text(
                                    "Selecione",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.surface
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Fastfood,
                                    contentDescription = stringResource(R.string.comida),
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            },
                            shape = RoundedCornerShape(16.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.surface,
                                unfocusedBorderColor = MaterialTheme.colorScheme.surface,
                                focusedTextColor = MaterialTheme.colorScheme.surface,
                                unfocusedTextColor = MaterialTheme.colorScheme.surface
                            ),

                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 8.dp)
                        )

                        ExposedDropdownMenu(
                            expanded = expandedTipo,
                            onDismissRequest = { expandedTipo = false }
                        ) {


                            DropdownMenuItem(
                                text = {
                                    Text(
                                        "Muita carne",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                },
                                onClick = {
                                    tipoSelecionado = "Muita carne"
                                    expandedTipo = false
                                }
                            )

                            DropdownMenuItem(
                                text = {
                                    Text(
                                        "Pouca carne",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                },
                                onClick = {
                                    tipoSelecionado = "Pouca carne"
                                    expandedTipo = false
                                }
                            )

                            DropdownMenuItem(
                                text = {
                                    Text(
                                        "Vegetariana",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                },
                                onClick = {
                                    tipoSelecionado = "Vegetariana"
                                    expandedTipo = false
                                }
                            )

                            DropdownMenuItem(
                                text = {
                                    Text(
                                        "Vegana",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                },
                                onClick = {
                                    tipoSelecionado = "Vegana"
                                    expandedTipo = false
                                }
                            )
                        }
                    }

                    OutlinedTextField(
                        value = energia,
                        singleLine = true,
                        onValueChange = {
                            energia = it
                        },
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                            .fillMaxWidth(),
                        label = {
                            Text(
                                text = "Consumo de energia",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.surface
                            )
                        },
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.surface,
                            unfocusedBorderColor = MaterialTheme.colorScheme.surface,
                            focusedTextColor = MaterialTheme.colorScheme.surface,
                            unfocusedTextColor = MaterialTheme.colorScheme.surface
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Bolt,
                                contentDescription = stringResource(R.string.quilometragem),
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )


                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {},
                        modifier = Modifier
                            .size(width = 150.dp, height = 48.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        elevation = ButtonDefaults
                            .buttonElevation(
                                defaultElevation = 6.dp,
                                pressedElevation = 2.dp
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = "Calcular",
                            color = MaterialTheme.colorScheme.surface,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    Image(
                        painter = painterResource(R.drawable.leaves),
                        contentDescription = stringResource(R.string.folhas),
                        modifier = Modifier
                            .padding(12.dp)
                            .size(120.dp)
                    )
                }
            }
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun MyBottomAppBar(navController: NavHostController) {
    val items = listOf(
        BottomNavigationItem(
            stringResource(R.string.hist_rico),
            icon = Icons.Default.History,
            route = Destination.Historico.route
        ),
        BottomNavigationItem(
            stringResource(R.string.in_cio),
            icon = Icons.Default.Home,
            route = Destination.Principal.route
        ),
        BottomNavigationItem(
            stringResource(R.string.perfil),
            icon = Icons.Default.Person,
            route = Destination.Atualizar.route
        ),
    )

    Surface(
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        color = MaterialTheme.colorScheme.secondary,
        tonalElevation = 8.dp,
        shadowElevation = 8.dp,
        modifier = Modifier.clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
    ) {
        NavigationBar(
            containerColor = Color.Transparent
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate(item.route)
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.displaySmall
                        )
                    }
                )
            }
        }
    }
}


