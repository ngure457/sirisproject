package com.example.sirisproject.models

data class ProductModel(
var productname: String="",
var productquantity: String="",
var productprice: String="",
var productbrand: String="",
var desc: String="",
var imageUrl: String="",
var productId: String=""
)
data class Event(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val location: String
)

data class SermonMedia(
    val id: String,
    val title: String,
    val speaker: String,
    val description: String,
    val date: String,
    val audioUrl: String,
    val videoUrl: String?
)

data class PrayerRequest(
    val id: String,
    val name: String,
    val request: String,
    val date: String,
    val isAnonymous: Boolean
)

data class BibleReading(
    val reference: String,
    val text: String,
    val version: String
)

