package com.mogo.rijogeorge.doctorsearch.Ui.searchScreen


import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mogo.rijogeorge.doctorsearch.R
import com.mogo.rijogeorge.doctorsearch.Ui.ListScreen.DoctorsListActivity
import com.mogo.rijogeorge.doctorsearch.databinding.ActivitySearchBinding


class SearchActivity : AppCompatActivity() {

    lateinit var binding : ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_search)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        val searchActivityViewModel = SearchActivityViewModel()
        binding.viewModel = searchActivityViewModel
        Handler().postDelayed({searchActivityViewModel.name = " new name"
            //binding.viewModel = searchActivityViewModel
        //binding.executePendingBindings()
        }, 5000)


        //startActivity(Intent(this, DoctorsListActivity::class.java))
    }
}
