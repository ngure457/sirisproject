package com.example.sirisproject.models


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

