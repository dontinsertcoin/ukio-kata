package ukio.kata.business

import ukio.kata.data.PaymentsData

interface ResultsDisplayer {

    fun showResults(results : List<PaymentsData>)

}
