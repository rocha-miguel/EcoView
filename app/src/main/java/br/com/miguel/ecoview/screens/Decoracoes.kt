package br.com.miguel.ecoview.screens

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.miguel.ecoview.ui.theme.EcoViewTheme

@Composable
fun TopStartCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .size(width = 160.dp, height = 85.dp),
        shape = RoundedCornerShape(
            bottomEnd = 85.dp
        ),
        colors = CardDefaults
            .cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
    )
    {}
}

@Preview
@Composable
private fun TopEndCardPreview() {
    EcoViewTheme {
        TopStartCard()

    }
}

@Composable
fun BottomEndCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .size(width = 160.dp, height = 85.dp),
        shape = RoundedCornerShape(
            topStart = 85.dp
        ),
        colors = CardDefaults
            .cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
    ) {}
}

@Preview
@Composable
private fun BottomStartCardPreview() {
    EcoViewTheme {
        BottomEndCard()

    }
}