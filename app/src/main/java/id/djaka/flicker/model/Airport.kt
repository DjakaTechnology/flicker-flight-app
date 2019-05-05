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
    var token: String? = ""
)

data class Gender(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = ""
)

@Parcelize
data class Passanger(
    @SerializedName("id")
    var id:Int? = 0,
    @SerializedName("gender_id")
    var genderId: Int? = 0,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("seat_code")
    var seatCode: String? = ""
) : Parcelable

@Parcelize
data class Reservation(
    @SerializedName("checkin_at")
    val checkinAt: String? = "",
    @SerializedName("cost")
    val cost: Int? = 0,
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("customer")
    val customer: Customer? = Customer(),
    @SerializedName("customer_id")
    val customerId: Int? = 0,
    @SerializedName("depart_at")
    val departAt: String? = "",
    @SerializedName("destination_id")
    val destinationId: Int? = 0,
    @SerializedName("gender_id")
    val genderId: Int? = 0,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("res_code")
    val resCode: String? = "",
    @SerializedName("res_date")
    val resDate: String? = "",
    @SerializedName("res_loc")
    val resLoc: String? = "",
    @SerializedName("route")
    val route: Route? = Route(),
    @SerializedName("route_id")
    val routeId: Int? = 0,
    @SerializedName("seat_code")
    val seatCode: String? = "",
    @SerializedName("staff_id")
    val staffId: Int? = 0,
    @SerializedName("status")
    val status: Status? = Status(),
    @SerializedName("status_id")
    val statusId: Int? = 0,
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("payment_proof")
    var paymentProof: String? = "",
    @SerializedName("try_count")
    val tryCount:Int? = 0
) : Parcelable

@Parcelize
data class Customer(
    @SerializedName("address")
    val address: String? = "",
    @SerializedName("birthdate")
    val birthdate: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("gender_id")
    val genderId: Int? = 0,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("phone")
    val phone: String? = ""
) : Parcelable

@Parcelize
data class Status(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = ""
) : Parcelable