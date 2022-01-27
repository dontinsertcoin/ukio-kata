package main.kotlin.ukio.kata.model

import java.time.LocalDate

data class Reservation(var initDate: LocalDate, var endDate: LocalDate, var apartment: Apartment)