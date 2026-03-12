package br.com.miguel.ecoview.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.miguel.ecoview.R


val ubuntuFamilly = FontFamily(
    Font(R.font.ubuntu_regular, FontWeight.Normal),
    Font(R.font.ubuntu_bold, FontWeight.Bold),
    Font(R.font.ubuntu_light, FontWeight.SemiBold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = ubuntuFamilly,
        fontWeight = FontWeight.SemiBold,
        fontSize = 40.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = ubuntuFamilly,
        fontWeight = FontWeight.SemiBold,
        fontSize = 36.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleSmall = TextStyle(
        fontFamily = ubuntuFamilly,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = ubuntuFamilly,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 100.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        fontFamily = ubuntuFamilly,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 200.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = TextStyle(
        fontFamily = ubuntuFamilly,
        fontWeight = FontWeight.Bold,
        fontSize = 64.sp,
        lineHeight = 68.sp,
        letterSpacing = 0.5.sp
    ),
    displayMedium = TextStyle(
        fontFamily = ubuntuFamilly,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.5.sp
    ),
    displaySmall = TextStyle(
        fontFamily = ubuntuFamilly,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 100.sp,
        letterSpacing = 0.5.sp
    )

)















































// Set of Material typography styles to start with
/* val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
     Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    )
    */
