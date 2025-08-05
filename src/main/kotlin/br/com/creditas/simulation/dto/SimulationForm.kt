package br.com.creditas.simulation.dto

import java.time.LocalDate

data class SimulationForm(
    val value: Double,
    val dateOfBirth: LocalDate,
    val paymentTerm: Int,
)
