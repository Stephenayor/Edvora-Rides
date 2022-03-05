package com.example.edvora_rides

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.edvora_rides.databinding.FragmentHomeBinding
import com.example.edvora_rides.model.User
import com.example.edvora_rides.network.RidesApi
import com.example.edvora_rides.network.RidesRetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container,
            false
        )

            var user = User()
            val ridesApi: RidesApi = RidesRetrofitClientInstance.getRetrofitInstance()
                ?.create(RidesApi::class.java) !!
            val call: Call<User> = ridesApi.getUserDetails()
            call.enqueue(object : Callback<User?> {
                override fun onResponse(call: Call<User?>, response: Response<User?>) {
                    Toast.makeText(context, "User DETAILS", Toast.LENGTH_LONG).show()
                    user = response.body()!!
                    binding.userName.text = response.body()?.name
                    context?.let {
                        Glide.with(it).load(response.body()!!.url)
                            .into(binding.userImage)
                    }
                }

                override fun onFailure(call: Call<User?>, t: Throwable) {
                    t.message
                }
            })

        return binding.root
    }

}