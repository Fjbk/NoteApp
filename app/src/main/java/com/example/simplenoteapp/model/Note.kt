package com.example.simplenoteapp.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class Note(
    val id: UUID = UUID.randomUUID(), //Genererer tilfældige ID-numre som man ikke behøver bekymre sig om
    val title: String,
    val description: String,
    val entryDate: LocalDateTime = LocalDateTime.now()
)
