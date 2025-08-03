package br.com.creditas.simulation.controller

import br.com.creditas.simulation.dto.SimulationForm
import br.com.creditas.simulation.dto.SimulationView
import br.com.creditas.simulation.service.CreditSimulationService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/simulate")
class SimulationController(
    val simulationService: CreditSimulationService,
) {

    @GetMapping
    fun getSimulation(
        @RequestParam(required = true)
        value: Double,
        @RequestParam(required = true)
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        dateOfBirth: LocalDate,
        @RequestParam(required = true)
        term: Int,
    ): ResponseEntity<SimulationView> {
        val simulationForm = SimulationForm(
            value = value,
            dateOfBirth = dateOfBirth,
            paymentTerm = term
        )
        val simulation = simulationService.simulate(simulationForm)
        return ResponseEntity.ok(simulation)
    }
}
