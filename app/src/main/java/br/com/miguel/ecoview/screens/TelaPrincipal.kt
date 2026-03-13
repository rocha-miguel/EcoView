package br.com.miguel.ecoview.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.miguel.ecoview.R
import br.com.miguel.ecoview.ui.theme.EcoViewTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TelaPrincipal() {
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
        TelaPrincipal()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
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
            containerColor = MaterialTheme.colorScheme.background
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
                                //navController.navigate(Destination.ProfileScreen.createRoute(email))

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
        MyTopAppBar()
    }
}

data class BottomNavigationItem(
    val tittle: String,
    val icon: ImageVector
)
@Composable
fun MyBottomAppBar() {
    val items = listOf(
        BottomNavigationItem(stringResource(R.string.hist_rico), icon = Icons.Default.History),
        BottomNavigationItem(stringResource(R.string.in_cio), icon = Icons.Default.Home),
        BottomNavigationItem(stringResource(R.string.perfil), icon = Icons.Default.Person),
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
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.tittle,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item.tittle,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.displaySmall
                        )
                    }
                )
            }
        }
    }
}

