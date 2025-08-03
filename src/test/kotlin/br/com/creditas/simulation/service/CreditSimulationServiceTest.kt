package br.com.creditas.simulation.service

import br.com.creditas.simulation.exception.BadRequestException
import br.com.creditas.simulation.model.SimulationForm
import br.com.creditas.simulation.model.SimulationView
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CreditSimulationServiceTest {

    private val creditSimulationService = CreditSimulationService()

    @Test
    fun `#getSimulation must return a credit simulation successfully`() {
        val expectedResult = SimulationView.build()
        val simulationForm = SimulationForm.build()

        val result = creditSimulationService.getSimulation(simulationForm)

        assertNotNull(result)
        assertEquals(expectedResult, result)
    }

    @Test
    fun `#getSimulation should throw BadRequest exception when age is invalid`() {
        val simulationForm = SimulationForm.wrongAge()

        val exception = assertThrows<BadRequestException> {
            creditSimulationService.getSimulation(simulationForm)
        }

        assertEquals(
            "Data de nascimento inválida. Tente novamente.",
            exception.message
        )
    }

    @Test
    fun `#getSimulation should throw BadRequest exception when value is invalid`() {
        val simulationForm = SimulationForm.wrongValue()

        val exception = assertThrows<BadRequestException> {
            creditSimulationService.getSimulation(simulationForm)
        }

        assertEquals(
            "Valor inválido. Tente novamente.",
            exception.message
        )
    }

    @Test
    fun `#getSimulation should throw BadRequest exception when payment term is invalid`() {
        val simulationForm = SimulationForm.wrongTerm()

        val exception = assertThrows<BadRequestException> {
            creditSimulationService.getSimulation(simulationForm)
        }

        assertEquals(
            "Prazo de pagamento inválido. Tente novamente.",
            exception.message
        )
    }

    @Test
    fun `#getSimulation should return correct interest rate for different birth dates`() {
        val simularionForm25 = SimulationForm.build()
        val simularionForm40 = SimulationForm.age40()
        val simularionForm60 = SimulationForm.age60()
        val simularionForm61 = SimulationForm.age61()

        val expectedResult25 = SimulationView.build()
        val expectedResult40 = SimulationView.build40()
        val expectedResult60 = SimulationView.build60()
        val expectedResult61 = SimulationView.build61()

        val result25 = creditSimulationService.getSimulation(simularionForm25)
        val result40 = creditSimulationService.getSimulation(simularionForm40)
        val result60 = creditSimulationService.getSimulation(simularionForm60)
        val result61 = creditSimulationService.getSimulation(simularionForm61)

        assertEquals(expectedResult25, result25)
        assertEquals(expectedResult40, result40)
        assertEquals(expectedResult60, result60)
        assertEquals(expectedResult61, result61)
    }
}
