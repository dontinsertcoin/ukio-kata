package ukio.kata

import ukio.kata.business.DataRetriver
import ukio.kata.business.PaymentSystem
import ukio.kata.business.ResultsDisplayer
import ukio.kata.business.actions.ConsolePrinter
import ukio.kata.business.actions.JsonDataRetriever
import ukio.kata.data.PaymentsData


fun main() {
    val dataRetriver : DataRetriver = JsonDataRetriever()
    val resultsDisplayer : ResultsDisplayer = ConsolePrinter()
    val paymentSystem = PaymentSystem()

    var invoices : MutableList<PaymentsData> = mutableListOf()
    val reservations = dataRetriver.retrieveData()
    reservations.forEach { reservation ->
        invoices.add(paymentSystem.buildInvoiceFrom(reservation))
    }
    resultsDisplayer.showResults(invoices)
}