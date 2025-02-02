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

    // Клиенты
    @GET("api/clients")
    fun getAllClients(): Call<List<Client>> // Получить всех клиентов

    @GET("api/clients/{id}")
    fun getClientById(@Path("id")clientId: Int): Call<Client>

    @POST("api/clients")
    fun createClient(@Body client: Client): Call<Client> // Создать нового клиента

    @PUT("api/clients/{id}")
    fun updateClient(@Path("id") clientId: Int, @Body client: Client): Call<Client> // Обновить клиента

    @DELETE("api/clients/{id}")
    fun deleteClient(@Path("id") clientId: Int): Call<Void> // Удалить клиента


    // Курьеры
    @GET("api/couriers")
    fun getAllCouriers(): Call<List<Courier>> // Получить всех курьеров

    @POST("api/couriers")
    fun createCourier(@Body courier: Courier): Call<Courier> // Создать нового курьера

    @PUT("api/couriers/{id}")
    fun updateCourier(@Path("id") courierId: Int, @Body courier: Courier): Call<Courier> // Обновить курьера

    @DELETE("api/couriers/{id}")
    fun deleteCourier(@Path("id") courierId: Int): Call<Void> // Удалить курьера


    // Заказы
    @GET("api/orders")
    fun getAllOrders(): Call<List<Order>> // Получить все заказы

    @POST("api/orders")
    fun createOrder(@Body order: Order): Call<Order> // Создать новый заказ

    @PUT("api/orders/{id}")
    fun updateOrder(@Path("id") orderId: Int, @Body order: Order): Call<Order> // Обновить заказ

    @DELETE("api/orders/{id}")
    fun deleteOrder(@Path("id") orderId: Int): Call<Void> // Удалить заказ
}
