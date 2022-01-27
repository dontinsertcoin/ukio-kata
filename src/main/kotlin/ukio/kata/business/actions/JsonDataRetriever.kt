package ukio.kata.business.actions

import com.google.gson.reflect.TypeToken
import main.kotlin.ukio.kata.model.Reservation
import ukio.kata.business.DataRetriver
import ukio.kata.helpers.GsonGenerator
import java.io.File

class JsonDataRetriever : DataRetriver {

    override fun retrieveData(): List<Reservation> {
        val gson = GsonGenerator.generate()
        val jsonString: String = File("./src/main/resources/data.json").readText(Charsets.UTF_8)
        val itemType = object : TypeToken<List<Reservation>>() {}.type
        return gson.fromJson<List<Reservation>>(jsonString, itemType)
    }
}
