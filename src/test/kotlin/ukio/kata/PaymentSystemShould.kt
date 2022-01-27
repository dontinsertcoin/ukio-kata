package ukio.kata
import main.kotlin.ukio.kata.model.Apartment
import main.kotlin.ukio.kata.model.Payment
import main.kotlin.ukio.kata.model.Reservation
import org.junit.Test
import ukio.kata.business.PaymentSystem
import ukio.kata.data.PaymentsData
import java.time.LocalDate
import kotlin.test.assertTrue

class PaymentSystemShould {

    val paymentSystem = PaymentSystem()

    @Test
    fun returnTheBookingPaymentAmountForLessThan4Months() {

        val apartment = givenAnApartmentWith(1500.0, 200.0)
        val reservation = givenAReservationFor(LocalDate.parse("2022-02-01"), LocalDate.parse("2022-03-01"), apartment)
        val reservation2 = givenAReservationFor(LocalDate.parse("2022-12-01"), LocalDate.parse("2023-01-31"), apartment)

        val paymentAmount = paymentSystem.calculateBookingPaymentAmount(reservation)
        val paymentAmount2 = paymentSystem.calculateBookingPaymentAmount(reservation2)

        assertTrue { paymentAmount.equals(1900.0) }
        assertTrue { paymentAmount2.equals(3600.0) }
    }

    @Test
    fun returnTheBookingPaymentAmountForMoreThan3Months() {
        val apartment = givenAnApartmentWith(1500.0, 200.0)
        val reservation = givenAReservationFor(LocalDate.parse("2022-02-01"), LocalDate.parse("2022-08-01"), apartment)
        val reservation2 = givenAReservationFor(LocalDate.parse("2022-02-01"), LocalDate.parse("2023-01-01"), apartment)

        val paymentAmount = paymentSystem.calculateBookingPaymentAmount(reservation)
        val paymentAmount2 = paymentSystem.calculateBookingPaymentAmount(reservation2)

        assertTrue { paymentAmount.equals(3400.0) }
        assertTrue { paymentAmount2.equals(3400.0) }
    }

    @Test
    fun returnTheInvoicesForLongTermReservations() {
        val apartment = givenAnApartmentWith(1500.0, 200.0)
        val expectedValue = PaymentsData(
            listOf(
                Payment(1700.0, LocalDate.parse("2022-02-28"), apartment),
                Payment(1700.0, LocalDate.parse("2022-03-31"), apartment),
                Payment(1900.0, LocalDate.parse("2022-04-30"), apartment)
            ),
            5300.0,
            0.0
        )

        val expectedValue2 = PaymentsData(
            listOf(
                Payment(1700.0, LocalDate.parse("2022-03-31"), apartment),
                Payment(1900.0, LocalDate.parse("2022-04-30"), apartment)
            ),
            7000.0,
            3400.0
        )

        val expectedValueHalfMonth = PaymentsData(
            listOf(
                Payment(850.0, LocalDate.parse("2022-02-28"), apartment),
                Payment(1700.0, LocalDate.parse("2022-03-31"), apartment),
                Payment(1900.0, LocalDate.parse("2022-04-30"), apartment)
            ),
            4450.0,
            0.0
        )

        val expectedValueHalfMonth2 = PaymentsData(
            listOf(
                Payment(1700.0, LocalDate.parse("2022-03-31"), apartment),
                Payment(1050.0, LocalDate.parse("2022-04-15"), apartment)
            ),
            6150.0,
            3400.0
        )

        val reservation = givenAReservationFor(LocalDate.parse("2022-01-01"), LocalDate.parse("2022-05-31"), apartment)
        val reservation2 = givenAReservationFor(LocalDate.parse("2022-02-01"), LocalDate.parse("2022-05-31"), apartment)
        val reservationHalfMonth = givenAReservationFor(LocalDate.parse("2022-01-15"), LocalDate.parse("2022-05-31"), apartment)
        val reservationHalfMonth2 = givenAReservationFor(LocalDate.parse("2022-02-01"), LocalDate.parse("2022-05-15"), apartment)

        val invoices = paymentSystem.buildInvoiceFrom(reservation)
        val invoices2 = paymentSystem.buildInvoiceFrom(reservation2)
        val invoicesHalfMonth = paymentSystem.buildInvoiceFrom(reservationHalfMonth)
        val invoicesHalfMonth2 = paymentSystem.buildInvoiceFrom(reservationHalfMonth2)

        assertTrue { invoices == expectedValue }
        assertTrue { invoices2 == expectedValue2 }
        assertTrue { invoicesHalfMonth == expectedValueHalfMonth }
        assertTrue { invoicesHalfMonth2 == expectedValueHalfMonth2 }
    }

    fun givenAReservationFor(initDate: LocalDate, endDate: LocalDate, apartment: Apartment) : Reservation {
        return Reservation(initDate, endDate, apartment)
    }

    fun givenAnApartmentWith(rentPerMonth: Double, utilitiesPerMonth: Double) : Apartment {
        return Apartment(rentPerMonth, utilitiesPerMonth)
    }
}
