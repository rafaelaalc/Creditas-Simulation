package br.com.creditas.simulation.model

import br.com.creditas.simulation.dto.SimulationForm
import java.time.LocalDate

object SimulationForm {
    fun build() = SimulationForm(
        value = 30000.0,
        dateOfBirth = LocalDate.now().minusYears(25),
        paymentTerm = 36,
    )

    fun wrongAge() = build().copy(dateOfBirth = LocalDate.now().minusYears(17))

    fun wrongValue() = build().copy(value = -50.5)

    fun wrongTerm() = build().copy(paymentTerm = 0)

    fun age40() = build().copy(dateOfBirth = LocalDate.now().minusYears(40))

    fun age60() = build().copy(dateOfBirth = LocalDate.now().minusYears(60))

    fun age61() = build().copy(dateOfBirth = LocalDate.now().minusYears(61))
}
