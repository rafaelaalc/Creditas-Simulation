package br.com.creditas.simulation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimulationApplication

fun main(args: Array<String>) {
	runApplication<SimulationApplication>(*args)
}
