package com.example.tourapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit
import android.content.Intent as Intent1


class login_otp_page : Fragment() {



    lateinit var  mauth : FirebaseAuth
    lateinit var  mcallback : PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var  ent_no : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view : View = inflater.inflate(R.layout.fragment_login_otp_page, container, false)

         ent_no = view.findViewById<EditText>(R.id.enter_no);
        val next_to_verifaction = view.findViewById<Button>(R.id.Next_to_verification);

        mauth = FirebaseAuth.getInstance()


        next_to_verifaction.setOnClickListener {
            if(ent_no.text.toString().trim().isEmpty()){
                Toast.makeText(activity,"enter number",Toast.LENGTH_LONG)
                return@setOnClickListener
            }else{
                optsend();
            }

        }




        return view;

    }
    fun optsend(){

        mcallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {



            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.d("otp faild","otp failderd")
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {

                 val action = login_otp_pageDirections.actionLoginOtpPageToVerificationPage(ent_no.text.toString())
                view?.findNavController()?.navigate(action)


            }
        }

        val options = PhoneAuthOptions.newBuilder(mauth)
            .setPhoneNumber(ent_no.text.toString())       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this.requireActivity())                 // Activity (for callback binding)
            .setCallbacks(mcallback)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)


    }


}