package com.example.tourapp

import android.content.ContentValues.TAG
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class verification_page : Fragment() {
    // TODO: Rename and change types of parameters

        lateinit var  args : String
        lateinit var  et_otp1 : EditText
        lateinit var  et_otp2 : EditText
        lateinit var  et_otp3 : EditText
        lateinit var  et_otp4 : EditText
        lateinit var  et_otp5 : EditText
        lateinit var  et_otp6 : EditText

        val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         arguments?.let {
            args  = verification_pageArgs.fromBundle(it).toString()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_verification_page, container, false)

        val next_to_dashboard : Button = view.findViewById(R.id.Next_to_Dashboard)

        et_otp1 = view.findViewById(R.id.otp_edit_box1)
        et_otp2 = view.findViewById(R.id.otp_edit_box2)
        et_otp3 = view.findViewById(R.id.otp_edit_box3)
        et_otp4 = view.findViewById(R.id.otp_edit_box4)
        et_otp5 = view.findViewById(R.id.otp_edit_box5)
        et_otp6 = view.findViewById(R.id.otp_edit_box6)

        next_to_dashboard.setOnClickListener {

            //it.findNavController().navigate(R.id.dashboard_page)

            if(et_otp1.text.toString().isEmpty()  ||
                et_otp2.text.toString().isEmpty() ||
                et_otp3.text.toString().isEmpty() ||
                et_otp4.text.toString().isEmpty() ||
                et_otp5.text.toString().isEmpty() ||
                et_otp6.text.toString().isEmpty() ){

                Toast.makeText(this.requireContext(),"opt is not valid",Toast.LENGTH_LONG).show()

            }else if(args != null){

               val code : String  =  et_otp1.text.toString() + et_otp2.text.toString() +et_otp3.text.toString() +et_otp4.text.toString()
                et_otp5.text.toString()+et_otp6.text.toString()

                val credential = PhoneAuthProvider.getCredential(args!! , code)

                signInWithPhoneAuthCredential(credential)
            }

        }





        // Inflate the layout for this fragment
        return view
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
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

}