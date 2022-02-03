package com.example.tourapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tourapp.Adpater.packageAdpater
import com.example.tourapp.model.package_model
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


class dashboard_page : Fragment() {

    private lateinit var adapter: packageAdpater;
    lateinit var recyclerView: RecyclerView
    lateinit var vm: ViewModel
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance();
    private val collectionReference: CollectionReference = db.collection("Products");




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view :View = inflater.inflate(R.layout.fragment_dashboard_page, container, false)

        recyclerView =view.findViewById(R.id.rcview1)


        return  view;

    }


    override fun onStart() {
        super.onStart()
        adapter.startListening();
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }


    fun setUpRecyclerView() {


        val query: Query = FirebaseFirestore.getInstance().collection("Products")//.orderBy("product_name", Query.Direction.ASCENDING);
        val options = FirestoreRecyclerOptions.Builder<package_model>()
            .setQuery(query, package_model::class.java)
            .build();

        adapter = packageAdpater(options)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter;



    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }
}

class Viewmodel {

}

