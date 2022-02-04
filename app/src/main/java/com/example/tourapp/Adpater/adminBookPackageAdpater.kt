package com.example.tourapp.Adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.ImageLoader
import coil.imageLoader
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.example.tourapp.R
import com.example.tourapp.model.booking_model
import com.example.tourapp.model.package_model
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore


class adminBookPackageAdpater(options: FirestoreRecyclerOptions<booking_model> ) :
    FirestoreRecyclerAdapter<booking_model, adminBookPackageAdpater.ViewHolder>(options) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var pakcage_Card = itemView.findViewById<CardView>(R.id.package_card)

        var u_phone = itemView.findViewById<TextView>(R.id.user_number)
        var total = itemView.findViewById<TextView>(R.id.price)
        var bookded_pack_name = itemView.findViewById<TextView>(R.id.booked_package)
        var date = itemView.findViewById<TextView>(R.id.date)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.bookedorder_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: booking_model) {


        holder.total.text = model.price
        holder.bookded_pack_name.text  = model.name
        holder.date.text = model.date.toString()
        holder.u_phone.text = model.u_phone


    }
}