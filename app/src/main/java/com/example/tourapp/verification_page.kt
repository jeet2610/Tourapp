package com.example.tourapp

import `in`.aabhasjindal.otptextview.OTPListener
import `in`.aabhasjindal.otptextview.OtpTextView
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.*
import java.util.concurrent.TimeUnit


class verification_page : Fragment() {


    private val args: verification_pageArgs by navArgs()


    lateinit var otpTextView: OtpTextView

    lateinit var mauth: FirebaseAuth
    lateinit var mcallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var next_to_dashboard: Button
     var otpNum=""
    var VerificationId = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mcallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                view?.findNavController()?.navigate(R.id.dashboard_page)

            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.d("otp faild", "otp failderd ${e.localizedMessage}")
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                VerificationId = verificationId

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mauth = FirebaseAuth.getInstance()
        val view: View = inflater.inflate(R.layout.fragment_verification_page, container, false)




        otpTextView = view.findViewById(R.id.otp_view);


        next_to_dashboard = view.findViewById(R.id.Next_to_Dashboard)


        Log.d(TAG, "onCreateView number: ${args.phoneNumber.toString()}")
        optsend(args.phoneNumber)

        otpTextView.otpListener = object : OTPListener {
            override fun onInteractionListener() {

            }

            override fun onOTPComplete(otp: String) {
                // fired when user has entered the OTP fully.
                Log.d(TAG, "onOTPComplete: $otp")
                otpNum = otp

            }
        }








        // Inflate the layout for this fragment
        return view
    }

    override fun onStart() {
        super.onStart()



        next_to_dashboard.setOnClickListener {

            next_to_dashboard.setOnClickListener(View.OnClickListener {
                if(VerificationId.isNotEmpty()){

                    Log.d(TAG, "onCreateView: ${VerificationId}${otpNum}")
                    val credential = PhoneAuthProvider.getCredential(VerificationId, otpNum)
                    signInWithPhoneAuthCredential(credential)
                } else{
                    Toast.makeText(this.requireContext(),"try Again",LENGTH_LONG).show()
                }

            })







        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mauth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    Toast.makeText(this.requireContext(),"opt is valid",Toast.LENGTH_LONG).show()
                    view?.findNavController()?.navigate(R.id.dashboard_page)

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    fun optsend(number :String){
if(number.contentEquals("+91")){
    val options = PhoneAuthOptions.newBuilder(mauth)
        .setPhoneNumber(number)       // Phone number to verify
        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
        .setActivity(this.requireActivity())                 // Activity (for callback binding)
        .setCallbacks(mcallback)          // OnVerificationStateChangedCallbacks
        .build()
    PhoneAuthProvider.verifyPhoneNumber(options)
}else{
    val options = PhoneAuthOptions.newBuilder(mauth)
        .setPhoneNumber("+91$number")       // Phone number to verify
        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
        .setActivity(this.requireActivity())                 // Activity (for callback binding)
        .setCallbacks(mcallback)          // OnVerificationStateChangedCallbacks
        .build()
    PhoneAuthProvider.verifyPhoneNumber(options)
}





    }




}

