package com.example.tourapp

import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.example.tourapp.Adpater.onClick
import com.example.tourapp.Adpater.packageAdpater
import com.example.tourapp.Adpater.packageAdpaterVertical
import com.example.tourapp.model.package_model
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlin.math.log


class dashboard_page : Fragment() ,onClick{

    private lateinit var adapter: packageAdpater;
    private lateinit var adapterVertical: packageAdpaterVertical;

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewVertical: RecyclerView
    lateinit var vm: ViewModel
    lateinit var logoutBtn:ImageView
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


   //     recyclerView =view.findViewById(R.id.rcview1)
        recyclerViewVertical =view.findViewById(R.id.rcview2)
        logoutBtn =view.findViewById(R.id.logoutbtn)


        return  view;

    }


    override fun onStart() {
        super.onStart()
       // adapter.startListening();
        adapterVertical.startListening()
        logoutBtn.setOnClickListener{view->
            Log.d(TAG, "onStop: CliCk logout")

                val builder = AlertDialog.Builder(this.requireContext())

                builder.apply {
                    this.setTitle("Do you want to logout?")
                    setPositiveButton("Cancel",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User clicked OK button
                        })
                    setNegativeButton("Logout",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User cancelled the dialog
                            FirebaseAuth.getInstance().signOut()
                            view.findNavController().navigate(R.id.home_page)
                        })
                }
                // Set other dialog properties


                // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()

        }


    }

    override fun onStop() {
        super.onStop()
      //  adapter.stopListening()
        adapterVertical.stopListening()


    }


  /*  fun setUpRecyclerView() {


        val query: Query = FirebaseFirestore.getInstance().collection("tours")//.orderBy("product_name", Query.Direction.ASCENDING);
        val options = FirestoreRecyclerOptions.Builder<package_model>()
            .setQuery(query, package_model::class.java)
            .build();

        adapter = packageAdpater(options)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
        recyclerView.adapter = adapter;



    }*/

    fun setUpRecyclerViewVertical() {


        val query: Query = FirebaseFirestore.getInstance().collection("tours")//.orderBy("product_name", Query.Direction.ASCENDING);
        val options = FirestoreRecyclerOptions.Builder<package_model>()
            .setQuery(query, package_model::class.java)
            .build();

        adapterVertical = packageAdpaterVertical(options,this)
        recyclerViewVertical.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        recyclerViewVertical.adapter = adapterVertical;



    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     //   setUpRecyclerView()
        setUpRecyclerViewVertical()
    }

    override fun onClickPackage(packageModel: package_model) {
        val action = dashboard_pageDirections.actionDashboardPageToTourDetailsPage(packageModel)
      requireView().findNavController().navigate(action)
    }
}



