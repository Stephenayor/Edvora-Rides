package com.example.edvora_rides.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edvora_rides.R
import com.example.edvora_rides.adapter.UpcomingRidesAdapter
import com.example.edvora_rides.databinding.FragmentUpcomingBinding
import com.example.edvora_rides.model.Rides
import com.example.edvora_rides.model.User
import com.example.edvora_rides.viewmodel.RidesViewModel
import com.example.edvora_rides.viewmodel.RidesViewModelFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class UpcomingFragment : Fragment() {
    lateinit var binding: FragmentUpcomingBinding
    lateinit var ridesViewModel: RidesViewModel
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentUpcomingBinding>(
            inflater, R.layout.fragment_upcoming, container,
            false
        )

        setupViewModel()
        getUserStationCode()
        ridesViewModel.getUpcomingRide()?.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                Log.d("API CALL", "STATUS 300")
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
                val formatted = current.format(formatter)

                val filteredList = list.filter { rides ->
                    rides?.date!! > formatted}
                displayUpcomingRides(filteredList)

        }})


        return binding.root
    }


    private fun getUserStationCode() {
        ridesViewModel.getUser()?.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                user = it
            }
        })
    }

    private fun setupViewModel() {
        val application = requireNotNull(this.activity).application
        val viewModelFactory = RidesViewModelFactory(application)
        ridesViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(RidesViewModel::class.java)
    }

    private fun displayUpcomingRides(upcomingRidesList: List<Rides?>) {
        val upcomingRidesAdapter = UpcomingRidesAdapter()
        if (upcomingRidesList != null) {
            upcomingRidesAdapter.ridesList = upcomingRidesList
        }
        binding.upcomingRidesRecyclerView.adapter = upcomingRidesAdapter
        val layoutManager = LinearLayoutManager.VERTICAL

        binding.upcomingRidesRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

}