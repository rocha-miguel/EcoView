package br.com.miguel.ecoview.screens

import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.util.Patterns
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.miguel.ecoview.R
import br.com.miguel.ecoview.ui.theme.EcoViewTheme


@Composable
fun TelaRegistrar(navController: NavController) {

    val context = LocalContext.current

    val placeholderImage = BitmapFactory
        .decodeResource(
            Resources.getSystem(),
            android.R.drawable.ic_menu_gallery
        )

    var profileImage by remember {
        mutableStateOf<Bitmap>(placeholderImage)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            val source = ImageDecoder.createSource(context.contentResolver, uri)
            profileImage = ImageDecoder.decodeBitmap(source)
        } else {
            profileImage = placeholderImage
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
    ) {
        TopStartCard(modifier = Modifier.align(Alignment.TopStart))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(alignment = Alignment.Center)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ComponenteTitulo()
            Spacer(modifier = Modifier.height(16.dp))
            ImagemUsuario(profileImage, launcher)
            RegistrarFormulario(navController, profileImage)
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
private fun TelaRegistrarPreview() {
    EcoViewTheme() {
        TelaRegistrar(rememberNavController())
    }
}


@Composable
fun ComponenteTitulo() {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.cadastro),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.displayLarge

        )
        Text(
            text = stringResource(R.string.crie_sua_conta),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.displaySmall
        )

    }


}

@Composable
fun ImagemUsuario(profileImage: Bitmap, launcher: ManagedActivityResultLauncher<String, Uri?>) {

    Box(
        modifier = Modifier
            .size(96.dp)
            .clickable(
                onClick = {
                    launcher.launch("image/*")
                }
            )
    ) {
        Image(
            modifier = Modifier
                .size(96.dp)
                .align(Alignment.Center)
                .clip(shape = CircleShape)
                .fillMaxSize(),
            bitmap = profileImage.asImageBitmap(),
            contentDescription = stringResource(R.string.imagem),
            contentScale = ContentScale.Crop
        )
        Icon(
            imageVector = Icons.Default.PhotoCamera,
            contentDescription = stringResource(R.string.c_mera_cone),
            modifier = Modifier
                .align(Alignment.BottomEnd),
            tint = MaterialTheme.colorScheme.primary
        )
    }

}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun ImagemUsuario() {
    EcoViewTheme() {
        //ImagemUsuario(profileImage, launcher)

    }
}

/*
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun ComponenteTituloPreview() {
    EcoViewTheme() {
        ComponenteTitulo()
    }

}
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarFormulario(navController: NavController, profileImage: Bitmap) {

    var nome by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var senha by remember {
        mutableStateOf("")
    }

    var isNameError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }

    var showDialogError by remember {
        mutableStateOf(false)
    }

    var showDialogSuccess by remember {
        mutableStateOf(false)
    }

    fun validate(): Boolean {
        isNameError = nome.length < 3
        isEmailError = email.length < 3 || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        isPasswordError = senha.length < 3
        return !isNameError && !isEmailError && !isPasswordError
    }


    //var userRepository = SharedPreferencesUserRepository(LocalContext.current)
    //var userRepository = RoomUserRepository(LocalContext.current)


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)

    ) {

        OutlinedTextField(
            value = nome,
            singleLine = true,
            onValueChange = {
                nome = it
            },
            modifier = Modifier
                .padding(vertical = 0.dp)
                .fillMaxWidth()
                .height(78.dp),
            label = {
                Text(
                    text = stringResource(R.string.seu_nome),
                    style = MaterialTheme.typography.bodySmall,
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults
                .colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,

                    ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(R.string.cone_pessoa),
                    tint = MaterialTheme.colorScheme.tertiary
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            isError = isNameError,
            trailingIcon = {
                if (isNameError) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = stringResource(R.string.erro),
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            supportingText = {
                if (isNameError) {
                    Text(
                        text = stringResource(R.string.nome_inv_lido),
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }
        )

        OutlinedTextField(
            value = email,
            singleLine = true,
            onValueChange = {
                email = it
            },
            modifier = Modifier
                .padding(vertical = 0.dp)
                .fillMaxWidth()
                .height(78.dp),
            label = {
                Text(
                    text = stringResource(R.string.seu_email),
                    style = MaterialTheme.typography.bodySmall,
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults
                .colors(
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
            ),
            isError = isEmailError,
            trailingIcon = {
                if (isEmailError) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            supportingText = {
                if (isEmailError) {
                    Text(
                        text = "Email is invalid",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }
        )

        OutlinedTextField(
            value = senha,
            singleLine = true,
            onValueChange = {
                senha = it
            },
            modifier = Modifier
                .padding(vertical = 0.dp)
                .fillMaxWidth()
                .height(78.dp),
            label = {
                Text(
                    text = "Sua senha",
                    style = MaterialTheme.typography.bodySmall,
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults
                .colors(
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
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            ),

            /*trailingIcon = {
                Icon (
                    imageVector = Icons.Default.RemoveRedEye,
                    contentDescription = stringResource(R.string.eye_icon),
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
           ,
             */
            isError = isPasswordError,
            trailingIcon = {
                if (isPasswordError) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            supportingText = {
                if (isPasswordError) {
                    Text(
                        text = "Password is invalid",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }
        )

        var expandedEstado by remember { mutableStateOf(false) }
        var expandedCidade by remember { mutableStateOf(false) }
        var estadoSelecionado by remember { mutableStateOf("") }
        var cidadeSelecionada by remember { mutableStateOf("") }

        ExposedDropdownMenuBox(
            expanded = expandedEstado,
            onExpandedChange = { expandedEstado = !expandedEstado },

            ) {
            OutlinedTextField(
                value = estadoSelecionado,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(
                        text = "Estado",
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                placeholder = { Text("Selecione") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary

                ),
                modifier = Modifier
                    .padding(vertical = 0.dp)
                    .menuAnchor()
                    .fillMaxWidth()

            )

            ExposedDropdownMenu(
                expanded = expandedEstado,
                onDismissRequest = { expandedEstado = false }


            ) {
                //para testes
                DropdownMenuItem(
                    text = { Text("Rio de Janeiro", style = MaterialTheme.typography.bodySmall) },
                    onClick = {
                        estadoSelecionado = "Rio de Janeiro"
                        expandedEstado = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("São Paulo", style = MaterialTheme.typography.bodySmall) },
                    onClick = {
                        estadoSelecionado = "São Paulo"
                        expandedEstado = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("Minas Gerais", style = MaterialTheme.typography.bodySmall) },
                    onClick = {
                        estadoSelecionado = "Minas Gerais"
                        expandedEstado = false
                    }
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        ExposedDropdownMenuBox(
            expanded = expandedCidade,
            onExpandedChange = { expandedCidade = !expandedCidade }
        ) {
            OutlinedTextField(
                value = cidadeSelecionada,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(
                        text = "Cidade",
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                placeholder = { Text("Selecione") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary
                ),

                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .padding(vertical = 0.dp)
            )

            ExposedDropdownMenu(
                expanded = expandedCidade,
                onDismissRequest = { expandedCidade = false }
            ) {

                //testes
                DropdownMenuItem(
                    text = { Text("Niterói", style = MaterialTheme.typography.bodySmall) },
                    onClick = {
                        cidadeSelecionada = "Niterói"
                        expandedCidade = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("São Gonçalo", style = MaterialTheme.typography.bodySmall) },
                    onClick = {
                        cidadeSelecionada = "São Gonçalo"
                        expandedCidade = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("Rio de Janeiro", style = MaterialTheme.typography.bodySmall) },
                    onClick = {
                        cidadeSelecionada = "Rio de Janeiro"
                        expandedCidade = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {


                /*if (validate()) {
                    userRepository.saveUser(
                        User(name = name,
                            email = email,
                            password = password,
                            userImage = convertBitmapToByteArray(profileImage)
                        )
                    )

                    showDialogSuccess = true


                } else {
                    showDialogError = true
                }*/
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = ButtonDefaults
                .buttonElevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 2.dp
                )
        )
        {
            Text(
                text = stringResource(R.string.criar_conta),
                color = MaterialTheme.colorScheme.surface,
                style = MaterialTheme.typography.labelMedium
            )
        }

        if (showDialogSuccess) {

            AlertDialog(
                onDismissRequest = { showDialogError = false },
                title = {
                    Text(
                        text = "Success"
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        showDialogSuccess = false
                        //navController.navigate(ComposeNavigator.Destination.route)
                    }
                    ) {
                        Text(text = "OK")
                    }

                }
            )

        }

        if (showDialogError) {
            AlertDialog(
                onDismissRequest = { showDialogError = false },
                title = {
                    Text(
                        text = "Error"
                    )

                },
                text = {
                    Text(
                        text = "Something went wrong"
                    )
                },
                confirmButton = {
                    TextButton(onClick = { showDialogError = false }) {
                        Text(text = "OK")
                    }
                },

                )
        }


    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun RegistrarFormularioPreview() {
    EcoViewTheme() {
        //RegistrarFormulario(rememberNavController(), profileImage)

    }

}
