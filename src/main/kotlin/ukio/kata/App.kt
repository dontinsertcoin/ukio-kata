package ukio.kata

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import main.kotlin.ukio.kata.model.Apartment
import main.kotlin.ukio.kata.model.Reservation
import ukio.kata.business.PaymentSystem
import ukio.kata.helpers.GsonGenerator
import ukio.kata.helpers.LocalDateDeserializer
import ukio.kata.helpers.LocalDateSerializer
import java.io.File
import java.time.LocalDate


fun main() {
    println("Started app")
    val paymentSystem = PaymentSystem()
    val gson = GsonGenerator.generate()
    println("Taking data...")
    val jsonString: String = File("./src/main/resources/data.json").readText(Charsets.UTF_8)
    println("Parsing data...")
    val itemType = object : TypeToken<List<Reservation>>() {}.type
    val reservations: List<Reservation> = gson.fromJson<List<Reservation>>(jsonString, itemType)
    println("Results...")
    reservations.forEach { reservation ->
        val invoices = paymentSystem.buildInvoiceFrom(reservation)
        println("$invoices")
    }

}