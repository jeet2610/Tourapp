package com.example.tourapp.Adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tourapp.R
import com.example.tourapp.model.package_model
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore


class packageAdpater(options: FirestoreRecyclerOptions<package_model> ) :
    FirestoreRecyclerAdapter<package_model, packageAdpater.ViewHolder>(options) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var pakcage_Card = itemView.findViewById<CardView>(R.id.package_card)
        var package_image = itemView.findViewById<ImageView>(R.id.package_img)
        var pacakge_name = itemView.findViewById<TextView>(R.id.package_name)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.package_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: package_model) {
        val url = model.package_img;
        Glide.with(holder.package_image.context)
            .load(url)
            .placeholder(R.drawable.ic_baseline_block_24)
            .into(holder.package_image)

        holder.pacakge_name?.text = model.package_name;

        FirebaseFirestore.getInstance().collection("Products").document()
            .get().addOnSuccessListener {
                model.package_id=  it.id
            }

    }
}