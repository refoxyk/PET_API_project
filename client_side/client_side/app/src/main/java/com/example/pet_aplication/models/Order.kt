package com.example.pet_aplication.models

class Order (
    val orderID : Int? = null,
    val klientID : Int? = null,
    val kurjerID : Int? = null,
    val deliveryAddress : String? = null,
    val deliveryFee : Float? = null,
    val status : String? = null,
    val createdAt : String? = null,
    val updatedAt : String? = null
)
