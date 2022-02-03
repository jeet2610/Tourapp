package com.example.tourapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.net.URL

@Parcelize
data class package_model(
    var package_id :String ="",
    val image : String ="",
    val name : String ="",
    val price :String="",
    val disp: String=""

):Parcelable

