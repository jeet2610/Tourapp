package com.example.tourapp


import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.tourapp.model.booking_model
import com.example.tourapp.viewmodel.razorpayViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.razorpay.Checkout
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class tour_details_page : Fragment() {

    val args: tour_details_pageArgs by navArgs()

    lateinit var package_nameTv: TextView
    lateinit var package_desTv: TextView
    lateinit var package_imageTv: ImageView
    lateinit var bookingbtn: Button
    lateinit var onpassengerchangebtn: TextView
    lateinit var ondatechangedbtn: TextView
    lateinit var departureDate: TextView
    lateinit var numberofpassengerTv: TextView
    lateinit var viewmodel:razorpayViewModel
    var cal = Calendar.getInstance()

    lateinit var auth: FirebaseAuth

    lateinit var BookModel: booking_model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tour_details_page, container, false)
        viewmodel = ViewModelProvider(requireActivity())[razorpayViewModel::class.java]

        package_desTv = view.findViewById(R.id.package_des)
        package_nameTv = view.findViewById(R.id.package_name)
        package_imageTv = view.findViewById(R.id.imageprofile)
        bookingbtn = view.findViewById(R.id.book_pack)
        onpassengerchangebtn = view.findViewById(R.id.onPassengerChangeBtn)
        ondatechangedbtn = view.findViewById(R.id.onDateChangedBtn)
        departureDate = view.findViewById(R.id.departureDate)
        numberofpassengerTv = view.findViewById(R.id.numberofpassengerTv)


        auth = FirebaseAuth.getInstance()

        /*firebase.auth().onAuthStateChanged((user) => {
            if (user) {
                // User logged in already or has just logged in.
                Log.d(TAG, "onCreateView: " + user.uid)
            } else {
                // User not logged in or has just logged out.
            }
        });*/



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

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

//
        bookingbtn.setOnClickListener {

           startPayment()
            val user = FirebaseAuth.getInstance().currentUser

                if( numberofpassengerTv.text.toString()!="0") {
                    BookModel = booking_model(
                        args.packageDetail.package_id,
                        args.packageDetail.name,
                        args.packageDetail.price,
                        null,
                        user!!.phoneNumber.toString(),
                        numberofpassengerTv.text.toString(),
                        departureDate.text.toString()
                    )
                    viewmodel.bookingmodel = BookModel
                }else {
                    // Snackbar.make(v,"Select number of Passengers",Snackbar.LENGTH_LONG).show()
                }
                Log.d(TAG, "User Phone Number :- " + user!!.phoneNumber)



        }
onpassengerchangebtn.setOnClickListener {
    showNumberPickerDialog()
}

        ondatechangedbtn.setOnClickListener {
            context?.let { it1 ->
                DatePickerDialog(
                    it1,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

        }
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        departureDate.text = sdf.format(cal.getTime()).toString()

    }

    fun showNumberPickerDialog() {
        val view = NumberPicker(requireContext())
        view.minValue = 1
        view.maxValue = 20
        view.wrapSelectorWheel = false
        view.value = 0
        AlertDialog.Builder(requireContext())
            .setView(view)
            .setPositiveButton(
                "set"
            ) { dialog, which ->
                numberofpassengerTv.text =view.value.toString()
            }
            .show()
    }

    private fun startPayment() {
        /*
        *  You need to pass current activity in order to let Razorpay create CheckoutActivity
        * */
        val activity: Activity =requireActivity()
        val co = Checkout()
        co.setKeyID("rzp_test_eP7GVgMsE9Nxr2")

        try {
            val options = JSONObject()
            options.put("name","Razorpay Corp")
            options.put("description","Demoing Charges")
            //You can omit the image option to fetch the image from dashboard
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("currency","INR")
            options.put("amount","100")
            options.put("send_sms_hash",true);

            val prefill = JSONObject()
            prefill.put("email","test@razorpay.com")
            prefill.put("contact","9021066696")

            options.put("prefill",prefill)
            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
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