package br.com.miguel.ecoview.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.miguel.ecoview.R
import br.com.miguel.ecoview.ui.theme.EcoViewTheme

@Composable
fun TelaInicial(

) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            )
    ) {

        TopStartCard(modifier = Modifier.align(Alignment.TopStart))

        Column(
            modifier = Modifier
                .padding(vertical = 150.dp, horizontal = 16.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.ecoview),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.displayLarge
                )
                Text(
                    text = stringResource(R.string.veja_seu_impacto_no_planeta),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.displaySmall
                )

            }

            Image(
                painter = painterResource(R.drawable.light_bulb),
                contentDescription = stringResource(R.string.homem_segurando_uma_l_mpada_com_uma_folha_dentro),
                modifier = Modifier
                    .padding(64.dp)
                    .size(200.dp)

            )

            Spacer(modifier = Modifier.height(50.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center

            ) {
                Button(
                    onClick = {},
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
                        text = stringResource(R.string.entrar),
                        color = MaterialTheme.colorScheme.surface,
                        style = MaterialTheme.typography.labelMedium,

                        )
                }

                Button(
                    onClick = {},
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
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(width = 150.dp, height = 48.dp)


                ) {
                    Text(
                        text = stringResource(R.string.registrar_se),
                        color = MaterialTheme.colorScheme.surface,
                        style = MaterialTheme.typography.labelMedium,

                        )
                }
            }

        }


        BottomEndCard(modifier = Modifier.align(Alignment.BottomEnd))
    }
}


@Preview
@Composable
private fun TelaInicialPreview() {

    EcoViewTheme() {
        TelaInicial()
    }

}