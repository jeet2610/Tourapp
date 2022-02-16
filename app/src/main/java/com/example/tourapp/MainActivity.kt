package com.example.tourapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.tourapp.viewmodel.razorpayViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class MainActivity : AppCompatActivity() , PaymentResultListener {
    lateinit var viewmodel :razorpayViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Checkout.preload(applicationContext)

        viewmodel = ViewModelProvider(this)[razorpayViewModel::class.java]

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

    }



    override fun onPaymentSuccess(p0: String?) {


        viewmodel.bookingmodel?.let {
            FirebaseFirestore.getInstance().collection("Booking").add(it)
                .addOnSuccessListener {

                    Snackbar.make(findViewById(android.R.id.content), "Success", Snackbar.LENGTH_LONG).show()

                }.addOnFailureListener {

                        Snackbar.make(findViewById(android.R.id.content), "booking fail", Snackbar.LENGTH_LONG).show()

                }
        }
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Snackbar.make(findViewById(android.R.id.content), "$p1", Snackbar.LENGTH_LONG).show()

    }
}