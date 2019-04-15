package id.djaka.flicker.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class AirPort(
    @SerializedName( "address")
    val address: String? = String(),
    @SerializedName( "city")
    val city: String? = "",
    @SerializedName( "code")
    val code: String? = "",
    @SerializedName( "id")
    val id: Int? = 0,
    @SerializedName( "name")
    val name: String? = ""
) : Parcelable

@Parcelize
data class Plane(
    @SerializedName( "airline")
    val airline: Airline? = Airline(),
    @SerializedName( "airline_id")
    val airlineId: Int? = 0,
    @SerializedName( "code")
    val code: String? = "",
    @SerializedName( "id")
    val id: Int? = 0,
    @SerializedName( "seat_column")
    val seatColumn: Int? = 0,
    @SerializedName( "seat_row")
    val seatRow: Int? = 0
) : Parcelable

@Parcelize
data class Airline(
    @SerializedName( "description")
    val description: String? = "",
    @SerializedName( "id")
    val id: Int? = 0,
    @SerializedName( "logo")
    val logo: String? = "",
    @SerializedName( "name")
    val name: String? = ""
) : Parcelable

@Parcelize
data class Route(
    @SerializedName("airport_from")
    val airportFrom: AirPort? = AirPort(),
    @SerializedName("airport_from_id")
    val airportFromId: Int? = 0,
    @SerializedName("airport_to")
    val airportTo: AirPort? = AirPort(),
    @SerializedName("airport_to_id")
    val airportToId: Int? = 0,
    @SerializedName("arrived_at")
    val arrivedAt: Date? = Date(),
    @SerializedName("depart_at")
    val departAt: Date? = Date(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("plane")
    val plane: Plane? = Plane(),
    @SerializedName("plane_id")
    val planeId: Int? = 0,
    @SerializedName("price")
    val price: Int? = 0
) : Parcelable

data class User(
    @SerializedName("address")
    val address: String? = "",
    @SerializedName("birthdate")
    val birthdate: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("gender")
    val gender: Gender? = Gender(),
    @SerializedName("gender_id")
    val genderId: Int? = 0,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("phone")
    val phone: String? = "",
    @SerializedName("token")
    val token: String? = ""
)

data class Gender(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = ""
)