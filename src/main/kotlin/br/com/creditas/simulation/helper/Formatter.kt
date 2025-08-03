package br.com.creditas.simulation.helper

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.Locale

fun formatAmount(value: Double): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return formatter.format(BigDecimal(value).setScale(2, RoundingMode.HALF_UP))
}
