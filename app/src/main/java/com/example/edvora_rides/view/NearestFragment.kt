package com.example.edvora_rides.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.edvora_rides.R
import com.example.edvora_rides.databinding.FragmentNearestBinding
import com.example.edvora_rides.model.Rides
import com.example.edvora_rides.network.RidesApi
import com.example.edvora_rides.network.RidesRetrofitClientInstance
import com.example.edvora_rides.viewmodel.RidesViewModel
import com.example.edvora_rides.viewmodel.RidesViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NearestFragment : Fragment() {
    lateinit var binding: FragmentNearestBinding
    lateinit var ridesViewModel: RidesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentNearestBinding>(
            inflater, R.layout.fragment_nearest, container,
            false)

        setupViewModel()
        ridesViewModel.getNearestRide()?.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                Log.d("API CALL", "STATUS 200")
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

}