package id.djaka.flicker.model
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AirPort(
    @Json(name = "address")
    val address: String? = String(),
    @Json(name = "city")
    val city: String? = "",
    @Json(name = "code")
    val code: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "name")
    val name: String? = ""
) : Parcelable

@Parcelize
data class Route(
    @Json(name = "airport_from_id")
    val airportFromId: Int? = 0,
    @Json(name = "airport_to_id")
    val airportToId: Int? = 0,
    @Json(name = "depart_at")
    val departAt: String = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "plane")
    val plane: Plane? = Plane(),
    @Json(name = "plane_id")
    val planeId: Int? = 0,
    @Json(name = "price")
    val price: Int? = 0
) : Parcelable

@Parcelize
data class Plane(
    @Json(name = "airline")
    val airline: Airline? = Airline(),
    @Json(name = "airline_id")
    val airlineId: Int? = 0,
    @Json(name = "code")
    val code: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "seat_column")
    val seatColumn: Int? = 0,
    @Json(name = "seat_row")
    val seatRow: Int? = 0
) : Parcelable

@Parcelize
data class Airline(
    @Json(name = "description")
    val description: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "logo")
    val logo: String? = "",
    @Json(name = "name")
    val name: String? = ""
) : Parcelable