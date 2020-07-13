package com.prayertime.view.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.prayertime.R
import com.prayertime.view.BaseFragment


class MainFragment : BaseFragment(), View.OnClickListener {

    var navController: NavController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.is_direction).setOnClickListener (this)
        view.findViewById<Button>(R.id.is_location).setOnClickListener (this)
        view.findViewById<Button>(R.id.is_more_menu).setOnClickListener (this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.is_location -> navController!!.navigate(R.id.action_mainFragment_to_locationFragment)
            R.id.is_direction -> navController!!.navigate(R.id.action_mainFragment_to_directionFragment)
            R.id.is_more_menu-> navController!!.navigate(R.id.action_mainFragment_to_moreFragment)

        }
    }


}