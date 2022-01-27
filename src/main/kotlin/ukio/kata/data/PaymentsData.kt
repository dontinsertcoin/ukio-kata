package ukio.kata.data

import main.kotlin.ukio.kata.model.Payment

data class PaymentsData (val payments: List<Payment>, val total: Double, val bookingPayment: Double) {
    override fun toString(): String {
        var text = if (payments.isNotEmpty()) "Pending payments:\n" else ""
        if (payments.isNotEmpty())
        payments.forEach { payment ->
            text += payment.toString()
        }
        if (bookingPayment != 0.0) {
            text += "Initial payment: $bookingPayment\n"
        }
        text += "Total: $total\n"
        return text
    }
}