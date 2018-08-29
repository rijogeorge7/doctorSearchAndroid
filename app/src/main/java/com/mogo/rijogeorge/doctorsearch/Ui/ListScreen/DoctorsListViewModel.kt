package com.mogo.rijogeorge.doctorsearch.Ui.ListScreen

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mogo.rijogeorge.data.DataManager
import com.mogo.rijogeorge.data.model.DoctorProfile
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DoctorsListViewModel(val dataManager: DataManager) : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    val doctorProfiles = MutableLiveData<List<DoctorProfile>>()
    fun fetchDoctorList() {
        val doctorSearchSubscription: Disposable = dataManager.getDoctorsInArea("37.773,-122.413,100",0,10,null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { doctorList -> updateDoctorList(doctorList)},
                        { error ->  fetchDoctorListFailed(error)}
                        )

        compositeDisposable.add(doctorSearchSubscription)
    }

    fun updateDoctorList(doctorList : List<DoctorProfile>) {
        doctorProfiles.value = doctorList
    }

    fun fetchDoctorListFailed(e: Throwable) {

    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}