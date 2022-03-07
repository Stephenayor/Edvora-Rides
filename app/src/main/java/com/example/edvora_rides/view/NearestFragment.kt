package com.example.edvora_rides.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edvora_rides.R
import com.example.edvora_rides.adapter.NearestRidesAdapter
import com.example.edvora_rides.databinding.FragmentNearestBinding
import com.example.edvora_rides.model.Rides
import com.example.edvora_rides.model.User
import com.example.edvora_rides.viewmodel.RidesViewModel
import com.example.edvora_rides.viewmodel.RidesViewModelFactory


class NearestFragment : Fragment() {
    lateinit var binding: FragmentNearestBinding
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
        binding = DataBindingUtil.inflate<FragmentNearestBinding>(
            inflater, R.layout.fragment_nearest, container,
            false
        )

        setupViewModel()
        getUserStationCode()
        ridesViewModel.getNearestRide()?.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                Log.d("API CALL", "STATUS 200")
                var rides: MutableList<Rides?>? = null
                var integerDifference: Int? = null

                for (i in list) {
                    for (stationPathIndex in 0 until list[id]?.stationPath?.size!!) {
                        if (user.stationCode?.minus((list[id]?.stationPath?.get(stationPathIndex)!!))
                                ?.let { it1 ->
                                    Math.abs(it1)
                                } == 0
                        ) {
                            list.get(id)
                            rides?.add(list.get(id))
                            displayNearestRides(rides)
                        } else {
                            var difference: Int =
                                user.stationCode?.minus(list[id]?.stationPath!!.get(stationPathIndex))
                                    ?.let { it1 ->
                                        Math.abs(
                                            it1
                                        )
                                    }!!
                            integerDifference = difference
                            if (difference <= integerDifference!!) {
                                var rideS: MutableList<Rides?>? = null
                                rideS?.add(list[id])
                                displayNearestRides(rideS)
                            }
                        }

                    }
                }
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

    private fun displayNearestRides(rideList: MutableList<Rides?>?) {
        val nearestRidesAdapter = NearestRidesAdapter()
        if (rideList != null) {
            nearestRidesAdapter.ridesList = rideList
        }
        binding.nearestRidesRecyclerView.adapter = nearestRidesAdapter
        val layoutManager = LinearLayoutManager.VERTICAL

        binding.nearestRidesRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

}