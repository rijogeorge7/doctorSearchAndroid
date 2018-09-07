package com.mogo.rijogeorge.doctorsearch.Ui.searchScreen


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mogo.rijogeorge.doctorsearch.R
import com.mogo.rijogeorge.doctorsearch.databinding.ActivitySearchBinding


class SearchActivity : AppCompatActivity() {

    lateinit var binding : ActivitySearchBinding
    val viewModel : SearchActivityViewModel by lazy {
        ViewModelProviders.of(this).get(SearchActivityViewModel::class.java)
    }
    val screenObserver = Observer<Screens> ( {screen -> updateSecreen(screen)})


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_search)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.viewModel = viewModel
        viewModel.currentScreen.observe(this, screenObserver)
        viewModel.setCurrentScreen(Screens.SEARCH)

        //startActivity(Intent(this, DoctorsListActivity::class.java))
        //showSearchScreen()
    }

    private fun updateSecreen(screen: Screens?) {
        when(screen) {
            Screens.SEARCH -> showSearchScreen()
            Screens.LOCATION -> showLocationInputScreen()
        }
    }

    private fun showLocationInputScreen() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.searchContainer, InputFragment.newInstance("Location"))
                .addToBackStack("location input")
                .commit()
    }

    private fun showSearchScreen() {
        supportFragmentManager.beginTransaction()
                .add(R.id.searchContainer, SearchFragment())
                .commit()
    }
}
