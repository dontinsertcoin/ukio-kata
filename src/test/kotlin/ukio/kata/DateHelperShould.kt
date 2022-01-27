package ukio.kata
import main.kotlin.ukio.kata.model.Apartment
import main.kotlin.ukio.kata.model.Reservation
import org.junit.Test
import ukio.kata.helpers.DateHelper
import java.time.LocalDate
import java.util.*
import kotlin.test.assertTrue

class DateHelperShould {

    @Test
    fun returnTheNumberOfMonthsBetweenTwoDates() {
        val date1 = LocalDate.parse("2022-02-01")
        val date2 = LocalDate.parse("2022-06-01")

        val months = DateHelper.calculateCompleteMonths(date1, date2)
        assertTrue { months.equals(4) }
    }

    @Test
    fun returnTheNumberOfMonthsBetweenTwoDatesWithDifferentDays() {
        val date1 = LocalDate.parse("2022-02-01")
        val date2 = LocalDate.parse("2022-05-31")

        val months = DateHelper.calculateCompleteMonths(date1, date2)
        assertTrue { months.equals(4) }
    }

    @Test
    fun returnTheNumberOfMonthsBetweenTwoDatesWithDifferentYear() {
        val date1 = LocalDate.parse("2022-12-01")
        val date2 = LocalDate.parse("2023-05-31")

        val months = DateHelper.calculateCompleteMonths(date1, date2)
        assertTrue { months.equals(6) }
    }
}
