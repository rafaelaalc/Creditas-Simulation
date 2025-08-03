package br.com.creditas.simulation.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class SimulationForm(
    val value: Double,
    @JsonFormat(pattern = "dd/MM/yyyy")
    val dateOfBirth: LocalDate,
    val paymentTerm: Int,
)
