package com.mogo.rijogeorge.doctorsearch.Ui.ListScreen

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.mogo.rijogeorge.data.DataManager

class DoctorsListViewModelFactory(val dataManager : DataManager) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DoctorsListViewModel::class.java)) {
            return DoctorsListViewModel(dataManager) as T
        }
        throw IllegalArgumentException("unknown view model class")
    }
}