package id.djaka.flicker.injection.network

import id.djaka.flicker.model.*
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
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

    @POST("register")
    @FormUrlEncoded
    fun register(@Field("name")name:String,
            @Field("email")email:String,
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

    @POST("customer/update")
    @FormUrlEncoded
    fun updateMe(@Field("id")id:Int,
                @Field("name")name: String,
                 @Field("address")address:String,
                 @Field("birthdate")birthdate:String,
                 @Field("gender_id")genderId: Int,
                 @Field("phone")phone:String):Deferred<User>

    @Multipart
    @POST("reservation/upload")
    fun uploadImg(@Part("id") id: RequestBody,
                  @Part img: MultipartBody.Part): Deferred<Response<Reservation>>
}