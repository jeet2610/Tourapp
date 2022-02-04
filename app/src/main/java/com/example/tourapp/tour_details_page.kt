package com.example.tourapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.tourapp.model.booking_model
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*


class tour_details_page : Fragment() {

 val args :tour_details_pageArgs by navArgs()

    lateinit var package_nameTv:TextView
    lateinit var package_desTv:TextView
    lateinit var package_imageTv :ImageView
    lateinit var bookingbtn :Button
    lateinit var  addbtn : ImageView
    lateinit var  minbtn : ImageView
    lateinit var  counttxt : TextView

    lateinit var auth: FirebaseAuth

    lateinit var BookModel : booking_model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tour_details_page, container, false)

          var count = 0
          package_desTv = view.findViewById(R.id.package_des)
          package_nameTv = view.findViewById(R.id.package_name)
        package_imageTv = view.findViewById(R.id.imageprofile)
        bookingbtn = view.findViewById(R.id.book_pack)
        addbtn = view.findViewById(R.id.counteradd)
        minbtn = view.findViewById(R.id.counterminus)
        counttxt = view.findViewById(R.id.countertext)

        auth = FirebaseAuth.getInstance()

        /*firebase.auth().onAuthStateChanged((user) => {
            if (user) {
                // User logged in already or has just logged in.
                Log.d(TAG, "onCreateView: " + user.uid)
            } else {
                // User not logged in or has just logged out.
            }
        });*/

        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            // User is signed in
            BookModel = booking_model(args.packageDetail.package_id,args.packageDetail.name,args.packageDetail.price,null,user.phoneNumber.toString()
            )
            Log.d(TAG, "User Phone Number :- "+ user.phoneNumber)
        } else {
            // No user is signed in
        }
        counttxt.setText("" +count)

        addbtn.setOnClickListener{

            counttxt.setText(""+ ++count)


        }

        minbtn.setOnClickListener {

            counttxt.setText(" "+ --count)

        }



        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
 Glide.with(package_imageTv.context)
     .load(args.packageDetail.image)
     .into(package_imageTv)
        package_nameTv.text = args.packageDetail.name
        package_desTv.text = args.packageDetail.disp
    }

    override fun onStart() {
        super.onStart()

        bookingbtn.setOnClickListener{
            FirebaseFirestore.getInstance().collection("Booking").add(BookModel)
                .addOnSuccessListener {
                    view?.let { it1 -> Snackbar.make(it1,"Success",Snackbar.LENGTH_LONG).show()

                    }
                }.addOnFailureListener{
                    view?.let { it1 -> Snackbar.make(it1,"booking fail",Snackbar.LENGTH_LONG).show() }
                }
        }
    }
}



/*
firebase.auth().onAuthStateChanged((user) => {
  if (user) {
    // User logged in already or has just logged in.
    console.log(user.uid);
  } else {
    // User not logged in or has just logged out.
  }
});
 */