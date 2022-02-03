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
import com.example.tourapp.model.package_model
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore


class packageAdpaterVertical(options: FirestoreRecyclerOptions<package_model> ) :
    FirestoreRecyclerAdapter<package_model, packageAdpaterVertical.ViewHolder>(options) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var pakcage_Card = itemView.findViewById<CardView>(R.id.package_card)
        var package_image = itemView.findViewById<ImageView>(R.id.package_img_vertical)
        var pacakge_name = itemView.findViewById<TextView>(R.id.package_name_vertical)
        var package_des = itemView.findViewById<TextView>(R.id.description)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.package_card_vertical, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: package_model) {
        val url = model.image;
      Glide.with(holder.package_image.context)
          .load(url)
          .placeholder(R.drawable.btn_trans)
          .into(holder.package_image)



        holder.pacakge_name?.text = model.name!!

        holder.package_des.text = model.disp


    }
}