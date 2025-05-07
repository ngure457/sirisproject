package com.example.sirisproject.data

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.io.File

class ProductViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance().reference.child("Products")

    private fun getImgurService(): ImgurService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.imgur.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ImgurService::class.java)
    }
    private fun getFileFromUri(context: Context, uri: Uri): File? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val file = File.createTempFile("temp_image", ".jpg", context.cacheDir)
            file.outputStream().use { output ->
                inputStream?.copyTo(output)
            }
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    fun uploadProductWithImage(
        uri: Uri,
        context: Context,
        productname: String,
        productquantity: String,
        productprice: String,
        productbrand: String,
        desc: String,
        navController: NavController





