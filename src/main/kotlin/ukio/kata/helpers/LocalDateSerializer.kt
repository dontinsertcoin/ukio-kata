package ukio.kata.helpers

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter


internal class LocalDateSerializer : JsonSerializer<LocalDate?> {
    override fun serialize(localDate: LocalDate?, srcType: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(formatter.format(localDate))
    }

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }
}