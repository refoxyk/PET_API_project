package com.example.pet_aplication.models

data class Courier (
    val kurjerID : Int? = null,
    val fullName : String? = null,
    val email : String? = null,
    val phoneNumber : String? = null,
    val batteryLevel : Float? = null,
    val available : Boolean? = null,
    val locationLatitude : Float? = null,
    val locationLongitude : Float? = null,
    val lastUpdate : String? = null,
)

