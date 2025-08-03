package br.com.creditas.simulation.dto

import java.time.LocalDateTime

data class ErrorView(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val name: String,
    val message: String?,
    val path: String,
)
