package com.example.tourapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tourapp.Adpater.adminBookPackageAdpater
import com.example.tourapp.Adpater.packageAdpater
import com.example.tourapp.model.booking_model
import com.example.tourapp.model.package_model
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query





class admin_page : Fragment() {
    // TODO: Rename and change types of parameters




    private lateinit var adapter: adminBookPackageAdpater
    lateinit var booked_recycleview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_admin_page, container, false)

        booked_recycleview =  view.findViewById<RecyclerView>(R.id.rcview3)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {

        val query: Query = FirebaseFirestore.getInstance().collection("booking")//.orderBy("product_name", Query.Direction.ASCENDING);
        val options = FirestoreRecyclerOptions.Builder<booking_model>()
            .setQuery(query, booking_model::class.java)
            .build();

        adapter = adminBookPackageAdpater(options)
        booked_recycleview.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
        booked_recycleview.adapter = adapter;

    }


}