package com.mogo.rijogeorge.doctorsearch.Ui.searchScreen

import android.arch.lifecycle.ViewModel
import android.databinding.BaseObservable
import android.databinding.Bindable
import com.mogo.rijogeorge.doctorsearch.BR

class SearchActivityViewModel : ViewModel() {
    //@get: Bindable
    var name:String = "Name from binding"
    set(value) {
        field = value
    }
}