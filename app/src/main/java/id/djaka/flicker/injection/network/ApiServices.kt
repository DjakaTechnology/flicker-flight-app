package id.djaka.flicker.injection.network

import id.djaka.flicker.model.AirPort
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiServices {
    /**
     * Get the list of the pots from the API
     */
    @GET("airport")
    fun getAirport(): Deferred<List<AirPort>>
}