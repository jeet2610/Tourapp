package com.example.tourapp

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider

import java.util.concurrent.TimeUnit


class student_login : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var  phoneNumber : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view : View = inflater.inflate(R.layout.fragment_register_page, container, false)

        val sign_intxt : TextView = view.findViewById(R.id.Sign_in)

        val phone_Number : EditText = view.findViewById(R.id.u_phone)
        val register : Button = view.findViewById(R.id.verification)

        phoneNumber = phone_Number.toString()
        auth.useAppLanguage()




        register.setOnClickListener {


        }



        sign_intxt.setOnClickListener {
            it.findNavController().navigate(R.id.verification_page)
        }


        return view
    }



}