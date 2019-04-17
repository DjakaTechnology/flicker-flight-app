package id.djaka.flicker.injection.network

import id.djaka.flicker.model.AirPort
import id.djaka.flicker.model.Passanger
import id.djaka.flicker.model.Route
import id.djaka.flicker.model.User
import kotlinx.coroutines.Deferred
import retrofit2.http.*

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

    @POST("login")
    @FormUrlEncoded
    fun login(@Field("email")email:String,
              @Field("password")password:String):Deferred<User>

    @GET("seat/{id}/taken")
    fun getTaken(@Path("id")routeId:Int):Deferred<List<Passanger>>
}