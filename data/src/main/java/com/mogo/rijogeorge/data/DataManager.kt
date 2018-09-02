package com.mogo.rijogeorge.data

import com.mogo.rijogeorge.data.model.DoctorProfile
import com.mogo.rijogeorge.data.model.Speciality
import io.reactivex.Observable

interface DataManager {
    fun getDoctorsInArea(location: String, skip: Int, limit: Int, specialty_uid: String?, insurance_uid: String?) : Observable<List<DoctorProfile>>
    fun getAvailableSpecialities(fields:String?, skip:Int, limit:Int?) : Observable<List<Speciality>>
}