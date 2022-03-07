package com.example.edvora_rides.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edvora_rides.R
import com.example.edvora_rides.adapter.NearestRidesAdapter
import com.example.edvora_rides.adapter.PastRidesAdapter
import com.example.edvora_rides.databinding.FragmentNearestBinding
import com.example.edvora_rides.databinding.FragmentPastBinding
import com.example.edvora_rides.model.Rides
import com.example.edvora_rides.model.User
import com.example.edvora_rides.viewmodel.RidesViewModel
import com.example.edvora_rides.viewmodel.RidesViewModelFactory


class PastFragment : Fragment() {
    lateinit var binding: FragmentPastBinding
    lateinit var ridesViewModel: RidesViewModel
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentPastBinding>(
            inflater, R.layout.fragment_past, container,
            false
        )

        setupViewModel()
        getUserStationCode()
        ridesViewModel.getPastRide()?.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                Log.d("API CALL", "STATUS 200")
                var rides: MutableList<Rides?>? = null
                var integerDifference: Int? = null

            }
        })
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

    private fun displayPastRides(rideList: MutableList<Rides?>?) {
        val pasttRidesAdapter = PastRidesAdapter()
        if (rideList != null) {
            pasttRidesAdapter.ridesList = rideList
        }
        binding.pastRidesRecyclerView.adapter = pasttRidesAdapter
        val layoutManager = LinearLayoutManager.VERTICAL

        binding.pastRidesRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

}