package com.mogo.rijogeorge.data.repository

import com.mogo.rijogeorge.data.model.Speciality

interface SpecialityRepository {
    fun getAvailableSpecialities(fields:String?, skip:Int, limit:Int?) : List<Speciality>
}