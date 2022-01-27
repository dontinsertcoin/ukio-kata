package ukio.kata.helpers

import main.kotlin.ukio.kata.model.Reservation
import java.time.LocalDate


class DateHelper {

    companion object {

        fun calculateCompleteMonths(initDate: LocalDate, endDate: LocalDate): Int {
            if (initDate.year <= endDate.year) {
                val numberOfYears = endDate.year - initDate.year
                val provisionalMonths = endDate.monthValue - initDate.monthValue + (12*numberOfYears)
                return if (initDate.dayOfMonth == 1 && endDate.dayOfMonth == endDate.lengthOfMonth()) {
                    provisionalMonths + 1
                } else if (initDate.dayOfMonth <= endDate.dayOfMonth) {
                    provisionalMonths
                } else {
                    provisionalMonths - 1
                }
            }
            return 0;
        }
    }
}