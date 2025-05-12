package com.example.sirisproject.network


import com.example.sirisproject.models.ImgurResponce
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.Response


interface ImgurService {
    @Multipart
    @POST("/3/image")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("Authorization") auth: String
    ): Response<ImgurResponce>

}