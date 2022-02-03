package com.example.tourapp

import android.os.Bundle
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
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class tour_details_page : Fragment() {

 val args :tour_details_pageArgs by navArgs()

    lateinit var package_nameTv:TextView
    lateinit var package_desTv:TextView
    lateinit var package_imageTv :ImageView
    lateinit var bookingbtn :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tour_details_page, container, false)

          package_desTv = view.findViewById(R.id.package_des)
          package_nameTv = view.findViewById(R.id.package_name)
        package_imageTv = view.findViewById(R.id.imageprofile)
        bookingbtn = view.findViewById(R.id.book_pack)
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
            FirebaseFirestore.getInstance().collection("Booking").add(args.packageDetail)
                .addOnSuccessListener {
                    view?.let { it1 -> Snackbar.make(it1,"Success",Snackbar.LENGTH_LONG).show() }
                }.addOnFailureListener{
                    view?.let { it1 -> Snackbar.make(it1,"booking fail",Snackbar.LENGTH_LONG).show() }
                }
        }
    }
}