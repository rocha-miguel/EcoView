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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.miguel.ecoview.R
import br.com.miguel.ecoview.model.Usuario
import br.com.miguel.ecoview.navigation.Destination
import br.com.miguel.ecoview.repository.RoomUsuarioRepository
import br.com.miguel.ecoview.service.Localidades
import br.com.miguel.ecoview.ui.theme.EcoViewTheme
import br.com.miguel.ecoview.utils.convertBitmapToByteArray
import br.com.miguel.ecoview.utils.convertByteArrayToBitmap


@Composable
fun TelaAtualizar(navController: NavController, email: String?) {

    val context = LocalContext.current
    val usuarioRepository = RoomUsuarioRepository(context)
    val usuario = email?.let { usuarioRepository.getUserByEmail(it) }

    val placeholderImage = BitmapFactory.decodeResource(
        Resources.getSystem(),
        android.R.drawable.ic_menu_gallery
    )

    val imagemInicial =
        usuario?.imagemUsuario?.let { convertByteArrayToBitmap(it) } ?: placeholderImage

    var profileImage by remember {
        mutableStateOf(imagemInicial)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            val source = ImageDecoder.createSource(context.contentResolver, uri)
            profileImage = ImageDecoder.decodeBitmap(source)
        } else {
            profileImage = imagemInicial
        }
    }

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
            ComponenteTituloAtualizar()
            Spacer(modifier = Modifier.height(16.dp))
            ImagemUsuarioAtualizar(profileImage, launcher)
            AtualizarFormulario(navController, profileImage, email)
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
private fun TelaAtualizarPreview() {
    EcoViewTheme() {
        TelaAtualizar(rememberNavController(), "")
    }
}


@Composable
fun ComponenteTituloAtualizar() {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.atualizar),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.displayLarge

        )
        Text(
            text = stringResource(R.string.atualize_seus_dados),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.displaySmall
        )

    }


}

@Composable
fun ImagemUsuarioAtualizar(
    profileImage: Bitmap,
    launcher: ManagedActivityResultLauncher<String, Uri?>
) {
    Box(
        modifier = Modifier
            .size(96.dp)
            .clickable {
                launcher.launch("image/*")
            }
    ) {
        Image(
            modifier = Modifier
                .size(96.dp)
                .align(Alignment.Center)
                .clip(CircleShape)
                .fillMaxSize(),
            bitmap = profileImage.asImageBitmap(),
            contentDescription = stringResource(R.string.imagem),
            contentScale = ContentScale.Crop
        )

        Icon(
            imageVector = Icons.Default.PhotoCamera,
            contentDescription = stringResource(R.string.c_mera_cone),
            modifier = Modifier.align(Alignment.BottomEnd),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun ImagemUsuarioAtualizar() {
    EcoViewTheme() {
        //ImagemUsuarioAtualizar(profileImage, launcher)

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
fun AtualizarFormulario(
    navController: NavController,
    imagemUsuario: Bitmap,
    usuarioEmail: String?
) {

    var usuarioRepository = RoomUsuarioRepository(LocalContext.current)

    var usuario = usuarioRepository.getUserByEmail(usuarioEmail!!)

    var nome by remember {
        mutableStateOf(usuario!!.nome)
    }

    var email by remember {
        mutableStateOf(usuario!!.email)
    }

    var senha by remember {
        mutableStateOf(usuario!!.senha)
    }

    var expandedEstado by remember { mutableStateOf(false) }
    var expandedCidade by remember { mutableStateOf(false) }
    var estadoSelecionado by remember { mutableStateOf(usuario!!.estado) }
    var cidadeSelecionada by remember { mutableStateOf(usuario!!.cidade) }
    var mostrarSenha by remember { mutableStateOf(false) }
    val estados = Localidades.estadosECidades.keys.toList()
    val cidades = Localidades.estadosECidades[estadoSelecionado] ?: emptyList()

    var isNameError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isEmailExistsError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }
    var isEstadoError by remember { mutableStateOf(false) }
    var isCidadeError by remember { mutableStateOf(false) }

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
        isEstadoError = estadoSelecionado.isBlank()
        isCidadeError = cidadeSelecionada.isBlank()
        isEmailExistsError = false

        return !isNameError &&
                !isEmailError &&
                !isPasswordError &&
                !isEstadoError &&
                !isCidadeError
    }

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }


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
                .fillMaxWidth(),
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
                .fillMaxWidth(),
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
                        contentDescription = stringResource(R.string.erro),
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            supportingText = {
                if (isEmailError) {
                    Text(
                        text = stringResource(R.string.email_inv_lido),
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
                .fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(R.string.sua_senha),
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
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),

            isError = isPasswordError,
            trailingIcon = {
                if (isPasswordError) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = stringResource(R.string.erro),
                        tint = MaterialTheme.colorScheme.error
                    )
                } else {
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
                }
            },
            visualTransformation = if (mostrarSenha) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            supportingText = {
                if (isPasswordError) {
                    Text(
                        text = stringResource(R.string.senha_inv_lida),
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }
        )


        ExposedDropdownMenuBox(
            expanded = expandedEstado,
            onExpandedChange = { expandedEstado = !expandedEstado }
        ) {
            OutlinedTextField(
                value = estadoSelecionado,
                onValueChange = {},
                readOnly = true,
                isError = isEstadoError,
                label = {
                    Text(
                        text = "Estado",
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                placeholder = { Text(stringResource(R.string.selecione)) },
                trailingIcon = {
                    if (isEstadoError) {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = stringResource(R.string.erro),
                            tint = MaterialTheme.colorScheme.error
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                },
                supportingText = {
                    if (isEstadoError) {
                        Text(
                            text = stringResource(R.string.selecione_um_estado),
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )
                    }
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
                estados.forEach { estado ->
                    DropdownMenuItem(
                        text = { Text(estado, style = MaterialTheme.typography.bodySmall) },
                        onClick = {
                            estadoSelecionado = estado
                            cidadeSelecionada = ""
                            isEstadoError = false
                            expandedEstado = false
                        }
                    )
                }
            }
        }
        Spacer(Modifier.height(12.dp))

        ExposedDropdownMenuBox(
            expanded = expandedCidade,
            onExpandedChange = {
                if (estadoSelecionado.isNotBlank()) {
                    expandedCidade = !expandedCidade
                }
            }
        ) {
            OutlinedTextField(
                value = cidadeSelecionada,
                onValueChange = {},
                readOnly = true,
                isError = isCidadeError,
                label = {
                    Text(
                        text = stringResource(R.string.cidade),
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                placeholder = { Text(stringResource(R.string.selecione)) },
                trailingIcon = {
                    if (isCidadeError) {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = stringResource(R.string.erro),
                            tint = MaterialTheme.colorScheme.error
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                },
                supportingText = {
                    if (isCidadeError) {
                        Text(
                            text = stringResource(R.string.selecione_uma_cidade),
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )
                    }
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
                cidades.forEach { cidade ->
                    DropdownMenuItem(
                        text = { Text(cidade, style = MaterialTheme.typography.bodySmall) },
                        onClick = {
                            cidadeSelecionada = cidade
                            isCidadeError = false
                            expandedCidade = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ) {
            Button(
                onClick = {
                    isEmailExistsError = false

                    if (validate()) {
                        val usuarioExistente = usuarioRepository.getUserByEmail(email)

                        if (usuarioExistente != null && usuarioExistente.id != usuario!!.id) {
                            isEmailExistsError = true
                            showDialogError = true
                            isEmailError = true
                        } else {
                            usuarioRepository.updateUser(
                                Usuario(
                                    id = usuario!!.id,
                                    nome = nome,
                                    email = email,
                                    senha = senha,
                                    estado = estadoSelecionado,
                                    cidade = cidadeSelecionada,
                                    imagemUsuario = convertBitmapToByteArray(imagemUsuario)
                                )
                            )

                            showDialogSuccess = true
                        }
                    } else {
                        showDialogError = true
                    }
                },
                colors = ButtonDefaults
                    .buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
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
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(width = 150.dp, height = 48.dp)


            ) {

                Text(
                    text = stringResource(R.string.atualizar),
                    color = MaterialTheme.colorScheme.surface,
                    style = MaterialTheme.typography.labelMedium,

                    )
            }

            Button(
                onClick = {
                    showDeleteDialog = true
                },
                colors = ButtonDefaults
                    .buttonColors(
                        containerColor = Color(0xFFD90C0C)
                    ),
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
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(width = 150.dp, height = 48.dp)


            ) {
                Text(
                    text = stringResource(R.string.excluir),
                    color = MaterialTheme.colorScheme.surface,
                    style = MaterialTheme.typography.labelMedium,

                    )
            }
        }


        if (showDialogSuccess) {

            AlertDialog(
                onDismissRequest = { showDialogSuccess = false },
                title = {
                    Text(
                        text = stringResource(R.string.conta_atualizada)
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        showDialogSuccess = false
                        navController.navigate(Destination.Principal.createRoute(email))
                    }
                    ) {
                        Text(text = stringResource(R.string.avan_ar))
                    }

                }
            )

        }

        if (showDialogError) {
            AlertDialog(
                onDismissRequest = { showDialogError = false },
                title = {
                    Text(
                        text = stringResource(R.string.erroo)
                    )

                },
                text = {
                    Text(
                        text = stringResource(R.string.algo_deu_errado)
                    )
                },
                confirmButton = {
                    TextButton(onClick = { showDialogError = false }) {
                        Text(text = stringResource(R.string.tentar_novamente))
                    }
                },

                )
        }

        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = {
                    Text(
                        text = stringResource(R.string.excluir_conta)
                    )
                },
                text = {
                    Text(
                        text = stringResource(R.string.tem_certeza_que_deseja_excluir_sua_conta)
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        showDeleteDialog = false
                        if (usuario != null) {
                            usuarioRepository.deleteUser(usuario)
                            navController.navigate(Destination.Entrar.route)

                        }
                    }) {
                        Text(text = stringResource(R.string.excluir))
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDeleteDialog = false

                    }) {
                        Text(text = stringResource(R.string.cancelar))
                    }
                }
            )
        }


    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun AtualizarFormularioPreview() {
    EcoViewTheme() {
        //AtualizarFormulario(rememberNavController(), profileImage)

    }

}
