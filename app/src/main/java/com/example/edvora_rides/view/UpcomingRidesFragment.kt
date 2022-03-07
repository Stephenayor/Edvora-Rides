package com.example.edvora_rides.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.google.android.material.snackbar.Snackbar


class UpcomingRidesFragment : Fragment() {
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
        ridesViewModel.getUpcomingRide()?.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                var currentDate: String? = ridesViewModel.getCurrentDate()
                val filteredUpcomingRidesList = list.filter { rides ->
                    rides?.date!! > currentDate!!
                }
                displayUpcomingRides(filteredUpcomingRidesList)
            }
        })

        return binding.root
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
        Toast.makeText(context, "NO RIDES AVAILABLE", Toast.LENGTH_LONG).show()
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