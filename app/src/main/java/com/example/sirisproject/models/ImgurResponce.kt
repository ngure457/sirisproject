package com.example.sirisproject.models

data class ImgurResponce(
    val data: ImgurData,
    val success: Boolean,
    val status: Int
)
data class ImgurData(
    val link: String
)