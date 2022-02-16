package com.example.tourapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.tourapp.model.booking_model

class razorpayViewModel(application: Application) :AndroidViewModel(application) {
    var bookingmodel:booking_model? = null
}