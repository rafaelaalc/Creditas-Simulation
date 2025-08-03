package br.com.creditas.simulation.helper

import br.com.creditas.simulation.dto.SimulationForm
import br.com.creditas.simulation.exception.BadRequestException

fun validateForm(simulationForm: SimulationForm) {
    if (simulationForm.value <= 0) {
        throw BadRequestException(
            "Valor solicitado inválido. Por favor, Tente novamente.",
        )
    }

    if (simulationForm.paymentTerm <= 0) {
        throw BadRequestException(
            "Prazo de pagamento inválido. Tente novamente.",
        )
    }
}
