package ukio.kata.helpers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.time.LocalDate

class GsonGenerator {

    companion object {
        fun generate() : Gson {
            val gsonBuilder = GsonBuilder()
            gsonBuilder.registerTypeAdapter(LocalDate::class.java, LocalDateSerializer())
            gsonBuilder.registerTypeAdapter(LocalDate::class.java, LocalDateDeserializer())
            return gsonBuilder.create()
        }
    }
}