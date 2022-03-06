package com.example.edvora_rides.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.edvora_rides.R
import com.example.edvora_rides.databinding.FragmentHomeBinding
import com.example.edvora_rides.viewmodel.RidesViewModel
import com.example.edvora_rides.viewmodel.RidesViewModelFactory

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var ridesViewModel: RidesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container,
            false
        )

        setupViewModel()
        ridesViewModel.getUser()?.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                binding.userName.text = list.name
                context?.let { it1 ->
                    Glide.with(it1).load(list.url)
                        .into(binding.userImage)
                }
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pager.adapter = PagerAdapter(childFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.pager)
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