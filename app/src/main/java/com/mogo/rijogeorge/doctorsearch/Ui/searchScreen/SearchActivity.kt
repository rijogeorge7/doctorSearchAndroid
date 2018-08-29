package com.mogo.rijogeorge.doctorsearch.Ui.searchScreen


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mogo.rijogeorge.doctorsearch.R
import com.mogo.rijogeorge.doctorsearch.Ui.ListScreen.DoctorsListActivity


class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        startActivity(Intent(this, DoctorsListActivity::class.java))
    }
}
