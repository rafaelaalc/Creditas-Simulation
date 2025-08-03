package br.com.creditas.simulation.controller

import br.com.creditas.simulation.dto.SimulationForm
import br.com.creditas.simulation.dto.SimulationView
import br.com.creditas.simulation.service.CreditSimulationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/simulate")
class SimulationController(
    val simulationService: CreditSimulationService,
) {

    @PostMapping
    fun simulate(
        @RequestBody form: SimulationForm
    ): ResponseEntity<SimulationView> {
        val simulation = simulationService.getSimulation(form)
        return ResponseEntity.ok(simulation)
    }
}
