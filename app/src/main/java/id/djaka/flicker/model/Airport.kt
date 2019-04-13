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