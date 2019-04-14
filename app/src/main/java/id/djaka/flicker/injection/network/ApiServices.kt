package id.djaka.flicker.injection.network

import id.djaka.flicker.model.AirPort
import id.djaka.flicker.model.Route
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    /**
     * Get the list of the pots from the API
     */
    @GET("airport")
    fun getAirport(): Deferred<List<AirPort>>

    @GET("route/{from}/{to}/{depart}")
    fun getRoute(@Path("from")from:Int,
                 @Path("to")to:Int,
                 @Path("depart")depart:String
    ):Deferred<List<Route>>
}