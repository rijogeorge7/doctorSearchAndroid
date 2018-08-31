package com.mogo.rijogeorge.data

import com.mogo.rijogeorge.data.di.components.DaggerDataComponent
import com.mogo.rijogeorge.data.di.components.DataComponent
import com.mogo.rijogeorge.data.model.DoctorProfile
import com.mogo.rijogeorge.data.repository.DoctorsRepository
import io.reactivex.Observable
import javax.inject.Inject

class DataManagerImpl @Inject constructor() : DataManager{
    companion object {
        val dataComponent : DataComponent by lazy {
            DaggerDataComponent.create()
        }
    }
    init {
        dataComponent.inject(this)
    }

    @Inject
    lateinit var doctorsRepository : DoctorsRepository

    override fun getDoctorsInArea(location: String, skip: Int, limit: Int, specialty_uid: String?, insurance_uid: String?) : Observable<List<DoctorProfile>> {

        val observable = Observable.create<List<DoctorProfile>> {
            subScriber ->
            var doctorsList = doctorsRepository.getDoctorsInArea(location, skip, limit, specialty_uid, insurance_uid)
            subScriber.onNext(doctorsList)
            subScriber.onComplete()
        }

        return observable
    }

}