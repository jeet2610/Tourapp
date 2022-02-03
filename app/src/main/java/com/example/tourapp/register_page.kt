package com.example.tourapp

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
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


class register_page : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var  phoneNumber : String

    lateinit var  sign_intxt : TextView;

    private lateinit var phone_Number : EditText;
    lateinit var  register : Button ;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val view : View = inflater.inflate(R.layout.fragment_register_page, container, false)
        auth = FirebaseAuth.getInstance()

    sign_intxt  = view.findViewById(R.id.Sign_in)

    phone_Number  = view.findViewById(R.id.u_phone)
      register = view.findViewById(R.id.verification)



        auth.useAppLanguage()



return view




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register.setOnClickListener {
            phoneNumber = phone_Number.text.toString()
            Log.d(TAG, "onCreateView: number Register $phoneNumber")
            val action = register_pageDirections.actionRegisterPageToVerificationPage(phoneNumber)
            it.findNavController().navigate(action)
        }



        sign_intxt.setOnClickListener {
            it.findNavController().navigate(R.id.login_otp_page)
        }
    }


}