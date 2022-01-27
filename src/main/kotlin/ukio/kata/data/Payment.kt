package main.kotlin.ukio.kata.model

import java.time.LocalDate

data class Payment(val amount: Double, val paymentDay : LocalDate, val apartment: Apartment) {

    override fun toString(): String {
        val monthlyRent = if (apartment.rentPerMonth >= amount)
                apartment.rentPerMonth/2
            else
                apartment.rentPerMonth
        val utilities = if (monthlyRent == apartment.rentPerMonth)
                apartment.utilitiesPerMonth
            else
                apartment.utilitiesPerMonth/2
        var text = "    Amount: $amount -> " +
                "Monthly rent: $monthlyRent, " +
                "Monthly utilities: $utilities\n" +
                "    Payment date: $paymentDay\n"
        if ((utilities + monthlyRent) != amount) {
            text += "    Final cleaning: 200\n"
        }
        return text+"\n"
    }
}