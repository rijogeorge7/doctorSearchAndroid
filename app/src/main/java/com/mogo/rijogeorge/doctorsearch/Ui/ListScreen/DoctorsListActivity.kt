package com.mogo.rijogeorge.doctorsearch.Ui.ListScreen

import android.app.FragmentManager
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mogo.rijogeorge.data.DataManager
import com.mogo.rijogeorge.data.DataManagerImpl
import com.mogo.rijogeorge.data.model.DoctorProfile
import com.mogo.rijogeorge.doctorsearch.R
import javax.inject.Inject

class DoctorsListActivity : AppCompatActivity() {

    @Inject
    lateinit var dataManager: DataManager
    var profileList : List<DoctorProfile>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_list)

        val viewModelDoctors : DoctorsListViewModel by lazy {
            val factory = DoctorsListViewModelFactory(dataManager)
            ViewModelProviders.of(this, factory).get(DoctorsListViewModel::class.java)
        }
        dataManager = DataManagerImpl.dataComponent.getDataManager()
        viewModelDoctors.fetchDoctorList()
        viewModelDoctors.doctorProfiles.observe(this, Observer { profiles -> updateUi(profiles) })
        supportFragmentManager.
                beginTransaction().
                add(R.id.listScreen, DoctorListFragment()).
                commit()


    }

    private fun updateUi(profiles: List<DoctorProfile>?) {
        if(profiles!=null) {
            profileList = profiles
            val listFragment : DoctorListFragment = supportFragmentManager.findFragmentById(R.id.listScreen) as DoctorListFragment
            listFragment.refreshDoctorsList(profiles)
        }
    }
}
