package br.com.miguel.ecoview.screens

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.miguel.ecoview.R
import br.com.miguel.ecoview.navigation.Destination
import br.com.miguel.ecoview.repository.RoomUsuarioRepository
import br.com.miguel.ecoview.repository.UsuarioRepository
import br.com.miguel.ecoview.ui.theme.EcoViewTheme

@Composable
fun TelaEntrar(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopStartCard(modifier = Modifier.align(Alignment.TopStart))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ComponenteTituloEntrar()
            Spacer(modifier = Modifier.height(16.dp))
            EntrarFormulario(navController)
        }

        BottomEndCard(modifier = Modifier.align(Alignment.BottomEnd))
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = true
)
@Composable
private fun TelaEntrarPreview() {
    EcoViewTheme {
        TelaEntrar(rememberNavController())
    }
}

@Composable
fun ComponenteTituloEntrar() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.entrar),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = stringResource(R.string.por_favor_entre_para_continuar),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntrarFormulario(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var mostrarSenha by remember { mutableStateOf(false) }
    var authenticateError by remember { mutableStateOf(false) }

    var usuarioRepository: UsuarioRepository = RoomUsuarioRepository(LocalContext.current)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {

        OutlinedTextField(
            value = email,
            singleLine = true,
            onValueChange = {
                email = it
            },
            modifier = Modifier
                .padding(vertical = 0.dp)
                .fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(R.string.seu_email),
                    style = MaterialTheme.typography.bodySmall,
                )
            },
            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Mail,
                    contentDescription = stringResource(R.string.cone_email),
                    tint = MaterialTheme.colorScheme.tertiary
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            value = senha,
            singleLine = true,
            onValueChange = {
                senha = it
            },
            modifier = Modifier
                .padding(vertical = 0.dp)
                .fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(R.string.sua_senha),
                    style = MaterialTheme.typography.bodySmall,
                )
            },
            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = stringResource(R.string.cone_senha),
                    tint = MaterialTheme.colorScheme.tertiary
                )
            },
            trailingIcon = {
                val image = if (mostrarSenha) {
                    Icons.Default.Visibility
                } else {
                    Icons.Default.VisibilityOff
                }

                IconButton(
                    onClick = { mostrarSenha = !mostrarSenha }
                ) {
                    Icon(
                        imageVector = image,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (mostrarSenha) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val authenticate = usuarioRepository.login(email, senha)

                if (authenticate) {
                    val usuario = usuarioRepository.getUserByEmail(email)

                    if (usuario != null) {
                        navController.navigate(Destination.Principal.createRoute(usuario.email))
                    } else {
                        authenticateError = true
                    }
                } else {
                    authenticateError = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp,
                pressedElevation = 2.dp
            )
        ) {
            Text(
                text = stringResource(R.string.entrar),
                color = MaterialTheme.colorScheme.surface,
                style = MaterialTheme.typography.labelMedium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (authenticateError) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = stringResource(R.string.erro),
                    tint = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.email_ou_senha_inv_lidos),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(R.string.ainda_n_o_tem_uma_conta),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
            TextButton(
                onClick = {
                    navController.navigate(Destination.Registrar.route)
                }
            ) {
                Text(
                    text = stringResource(R.string.registrar_se),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun EntrarFormularioPreview() {
    EcoViewTheme {
        EntrarFormulario(rememberNavController())
    }
}