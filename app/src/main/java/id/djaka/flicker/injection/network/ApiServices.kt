package id.djaka.flicker.injection.network

import id.djaka.flicker.model.*
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

    @POST("reservation")
    @FormUrlEncoded
    fun insertReservation(@Field("customer_id")customerId:Int,
                          @Field("seat_code")seatCode:String,
                          @Field("route_id")routeId:Int,
                          @Field("name")name:String,
                          @Field("gender_id")genderId:Int):Deferred<Reservation>

    @GET("customer/{id}/reservation")
    fun getReservations(@Path("id")id:Int):Deferred<List<Reservation>>
}