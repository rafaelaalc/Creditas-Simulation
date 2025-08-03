package br.com.creditas.simulation.model

import br.com.creditas.simulation.dto.SimulationView

object SimulationView {
    fun build() = SimulationView(
        total = "R\$ 32.368,57",
        installmentsValue = "R\$ 899,13",
        totalInterest = "R\$ 2.368,57",
    )

    fun build40() = SimulationView(
        total = "R\$ 31.407,71",
        installmentsValue = "R\$ 872,44",
        totalInterest = "R\$ 1.407,71",
    )

    fun build60() = SimulationView(
        total = "R\$ 30.933,99",
        installmentsValue = "R\$ 859,28",
        totalInterest = "R\$ 933,99",
    )

    fun build61() = SimulationView(
        total = "R\$ 31.885,90",
        installmentsValue = "R\$ 885,72",
        totalInterest = "R\$ 1.885,90",
    )
}
