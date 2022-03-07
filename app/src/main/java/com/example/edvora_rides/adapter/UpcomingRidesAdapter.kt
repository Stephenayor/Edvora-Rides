package com.example.edvora_rides.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.edvora_rides.R
import com.example.edvora_rides.model.Rides

class UpcomingRidesAdapter() : RecyclerView.Adapter<UpcomingRidesAdapter.UpcomingRidesViewHolder>() {
    var ridesList: List<Rides?> = listOf<Rides>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingRidesViewHolder {
        return UpcomingRidesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.upcoming_rides_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: UpcomingRidesViewHolder, position: Int) {
        Glide.with(holder.upcomingRideImageView.context)
            .load(ridesList[position]?.mapUrl)
            .into(holder.upcomingRideImageView)
        holder.cityNameTextView.text = ridesList[position]?.city
        holder.stateNameTextView.text = ridesList[position]?.state
        holder.rideIDTextView.text = ridesList[position]?.id.toString()
        holder.originStationTextView.text = ridesList[position]?.originStationCode.toString()
        holder.stationPathTextView.text = ridesList[position]?.stationPath.toString()
        holder.dateTextView.text = ridesList[position]?.date
    }

    override fun getItemCount(): Int {
        return ridesList.size
    }

    class UpcomingRidesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val upcomingRideImageView = itemView.findViewById<ImageView>(R.id.upcoming_ride_image_view)!!
        val cityNameTextView = itemView.findViewById<TextView>(R.id.city_name_text_view)!!
        val stateNameTextView = itemView.findViewById<TextView>(R.id.state_name_text_view)!!
        val rideIDTextView = itemView.findViewById<TextView>(R.id.ride_id_text_view)!!
        val originStationTextView = itemView.findViewById<TextView>(R.id.origin_station_text_view)!!
        val  stationPathTextView = itemView.findViewById<TextView>(R.id.station_path_text_view)!!
        val dateTextView = itemView.findViewById<TextView>(R.id.date_text_view)!!
        val distance = itemView.findViewById<TextView>(R.id.distance_text_view)!!
    }


}