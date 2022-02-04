package com.example.tourapp.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp


data class booking_model(

    var package_id :String ="",
    val name : String ="",
    val price :String= " ",
    @ServerTimestamp
    val date : Timestamp?=null,
    val u_phone : String = " ",
    val passengers :String="",
    val departure :String=""

)
