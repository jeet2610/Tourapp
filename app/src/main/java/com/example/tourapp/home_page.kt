package com.example.tourapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController


class home_page : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view : View = inflater.inflate(R.layout.fragment_home_page, container, false)

        val studentbtn : Button = view.findViewById(R.id.Student)

        studentbtn.setOnClickListener {

            it.findNavController().navigate(R.id.register_page)
        }



            return view


    }




}