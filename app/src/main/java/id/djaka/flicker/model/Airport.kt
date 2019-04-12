package id.djaka.flicker.model
import com.squareup.moshi.Json

data class AirPort(
    @Json(name = "address")
    val address: Any? = Any(),
    @Json(name = "city")
    val city: String? = "",
    @Json(name = "code")
    val code: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "name")
    val name: String? = ""
)