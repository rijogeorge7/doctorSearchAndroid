package com.mogo.rijogeorge.doctorsearch.Ui.searchScreen

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mogo.rijogeorge.doctorsearch.R
import com.mogo.rijogeorge.doctorsearch.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    lateinit var binding : FragmentSearchBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate<FragmentSearchBinding>(inflater,R.layout.fragment_search, container, false)
        binding.viewModel = SearchActivityViewModel()
        val viewModel : SearchActivityViewModel by lazy {
            ViewModelProviders.of(activity as SearchActivity).get(SearchActivityViewModel::class.java)
        }
        binding.locationText.setOnClickListener( {v -> viewModel.locationClicked()} )
        return binding.root
    }



}
