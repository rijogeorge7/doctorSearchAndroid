package com.mogo.rijogeorge.doctorsearch.Ui.ListScreen


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mogo.rijogeorge.data.model.DoctorProfile

import com.mogo.rijogeorge.doctorsearch.R


class DoctorListFragment : Fragment() {

    private lateinit var doctorsListAdapter : DoctorListAdapter
    val context = activity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_doctor_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.doctorsRecyclerView).apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(activity)
            val profileList = (activity as DoctorsListActivity).profileList
                doctorsListAdapter = DoctorListAdapter(profileList, context)
                adapter = doctorsListAdapter
        }
        return view
    }

    fun refreshDoctorsList(profileList : List<DoctorProfile>) {
        doctorsListAdapter.refresh(profileList)
    }


}
