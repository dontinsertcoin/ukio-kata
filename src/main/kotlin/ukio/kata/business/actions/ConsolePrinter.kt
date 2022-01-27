package ukio.kata.business.actions

import ukio.kata.business.ResultsDisplayer
import ukio.kata.data.PaymentsData

class ConsolePrinter : ResultsDisplayer{
    override fun showResults(results: List<PaymentsData>) {
        println(results.toString())
    }


}
