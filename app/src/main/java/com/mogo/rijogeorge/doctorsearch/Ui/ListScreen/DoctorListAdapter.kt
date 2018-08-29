package com.mogo.rijogeorge.doctorsearch.Ui.ListScreen

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mogo.rijogeorge.data.model.DoctorProfile
import com.mogo.rijogeorge.doctorsearch.R
import com.mogo.rijogeorge.doctorsearch.R.id.imageView

class DoctorListAdapter (var profileList: List<DoctorProfile>?, val context:Context) : RecyclerView.Adapter<DoctorListAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.doctor_list_cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return profileList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = profileList?.get(position)?.slug
        holder.speciality.text = profileList?.get(position)?.speciality
        holder.distance.text = profileList?.get(position)?.distance.toString()
        holder.address.text = profileList?.get(position)?.address?.street
        Glide.with(context).load(profileList?.get(position)?.image_url).into(holder.profilePic)
    }

    fun refresh(profileList: List<DoctorProfile>) {
        this.profileList = profileList

        notifyDataSetChanged()
    }

    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.name)
        val speciality = view.findViewById<TextView>(R.id.speciality)
        val distance = view.findViewById<TextView>(R.id.distance)
        val address = view.findViewById<TextView>(R.id.address)
        val profilePic = view.findViewById<ImageView>(R.id.profilePic)
    }
}