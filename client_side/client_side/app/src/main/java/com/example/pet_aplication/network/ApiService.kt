package com.example.pet_aplication.network

import com.example.pet_aplication.models.Client
import com.example.pet_aplication.models.Courier
import com.example.pet_aplication.models.Order
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

 
    @GET("api/clients")
    fun getAllClients(): Call<List<Client>> 

    @GET("api/clients/{id}")
    fun getClientById(@Path("id")clientId: Int): Call<Client>

    @POST("api/clients")
    fun createClient(@Body client: Client): Call<Client> 

    @PUT("api/clients/{id}")
    fun updateClient(@Path("id") clientId: Int, @Body client: Client): Call<Client> 

    @DELETE("api/clients/{id}")
    fun deleteClient(@Path("id") clientId: Int): Call<Void>



    @GET("api/couriers")
    fun getAllCouriers(): Call<List<Courier>>

    @POST("api/couriers")
    fun createCourier(@Body courier: Courier): Call<Courier> 

    @PUT("api/couriers/{id}")
    fun updateCourier(@Path("id") courierId: Int, @Body courier: Courier): Call<Courier> 

    @DELETE("api/couriers/{id}")
    fun deleteCourier(@Path("id") courierId: Int): Call<Void> 



    @GET("api/orders")
    fun getAllOrders(): Call<List<Order>>

    @POST("api/orders")
    fun createOrder(@Body order: Order): Call<Order> 

    @PUT("api/orders/{id}")
    fun updateOrder(@Path("id") orderId: Int, @Body order: Order): Call<Order> 

    @DELETE("api/orders/{id}")
    fun deleteOrder(@Path("id") orderId: Int): Call<Void> 
}
