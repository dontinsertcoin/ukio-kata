package ukio.kata.business

import main.kotlin.ukio.kata.model.Reservation

interface DataRetriver {

    fun retrieveData() : List<Reservation>

}
