package br.com.creditas.simulation.service

import br.com.creditas.simulation.dto.SimulationForm
import br.com.creditas.simulation.dto.SimulationView
import br.com.creditas.simulation.exception.BadRequestException
import br.com.creditas.simulation.helper.formatAmount
import br.com.creditas.simulation.helper.validateForm
import org.springframework.stereotype.Service
import java.time.LocalDate
import kotlin.math.pow

private const val MONTHS_VALUE = 12
private const val HUNDRED_VALUE = 100
private const val FIVE_PERCENT = 5.0
private const val FOUR_PERCENT = 4.0
private const val TREE_PERCENT = 3.0
private const val TWO_PERCENT = 2.0

@Service
class CreditSimulationService {
    fun getSimulation(
        simulation: SimulationForm,
    ): SimulationView {
        validateForm(simulation)
        val annualInterestRate = getAnnualInterestRate(simulation.dateOfBirth)
        val monthlyInterestRate = (annualInterestRate / MONTHS_VALUE) / HUNDRED_VALUE
        val monthlyPayment = (simulation.value * monthlyInterestRate) /
            (1 - (1 + monthlyInterestRate).pow(-simulation.paymentTerm))

        val total = (monthlyPayment * simulation.paymentTerm)
        val totalInterest = (monthlyPayment * simulation.paymentTerm) - simulation.value
        return SimulationView(
            total = formatAmount(total),
            installmentsValue = formatAmount(monthlyPayment),
            totalInterest = formatAmount(totalInterest),
        )
    }

    private fun getAnnualInterestRate(dateOfBirth: LocalDate): Double {
        val age = (LocalDate.now().year) - (dateOfBirth.year)
        return when {
            age < 18 -> throw BadRequestException("Data de nascimento inválida. Tente novamente.")
            age <= 25 -> FIVE_PERCENT
            age in 26..40 -> TREE_PERCENT
            age in 41..60 -> TWO_PERCENT
            else -> FOUR_PERCENT
        }
    }
}
