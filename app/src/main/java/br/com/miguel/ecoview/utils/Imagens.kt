package br.com.miguel.ecoview.utils



import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

fun convertBitmapToByteArray(bitmap: Bitmap): ByteArray {

    val outputStream = ByteArrayOutputStream()
    bitmap.compress(
        Bitmap.CompressFormat.JPEG,
        75,
        outputStream
    )
    return outputStream.toByteArray(
    )

}

fun convertByteArrayToBitmap(imageArray: ByteArray): Bitmap {

    return BitmapFactory
        .decodeByteArray(
            imageArray,
            0,
            imageArray.size
        )
}