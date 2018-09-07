package com.mogo.rijogeorge.doctorsearch.Ui.searchScreen

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class SearchActivityViewModel : ViewModel() {

    var location = "current location"
    var speciality = "internal medicine"
    var insurence = "I will choose later"

    val currentScreen : MutableLiveData<Screens> = MutableLiveData<Screens>()


    fun locationClicked() {
        currentScreen.value = Screens.LOCATION
    }

    fun setCurrentScreen(screen: Screens) {
        currentScreen.value = screen
    }

}

enum class Screens {
    SEARCH,LOCATION,SPECIALITY,INSURENCE
}