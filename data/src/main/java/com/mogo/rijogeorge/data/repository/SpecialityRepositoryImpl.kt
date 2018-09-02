package com.mogo.rijogeorge.data.repository

import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.mogo.rijogeorge.data.DataManagerImpl
import com.mogo.rijogeorge.data.WebServices.DoctorService
import com.mogo.rijogeorge.data.model.Speciality
import javax.inject.Inject

class SpecialityRepositoryImpl @Inject constructor() : SpecialityRepository {

    init {
        DataManagerImpl.dataComponent.inject(this)
    }

    @Inject
    lateinit var doctorService: DoctorService

    override fun getAvailableSpecialities(fields: String?, skip: Int, limit: Int?): List<Speciality> {
        val callResponse = doctorService.getAvailableSpecialities(fields, skip, limit)
        val response = callResponse.execute()
        var jsonStr = response.body()
        val specialityList : List<Speciality> = getSpecialityListFromJsonStr(jsonStr)
        return specialityList
    }

    private fun getSpecialityListFromJsonStr(jsonStr: String?): List<Speciality> {
        val specialityList = ArrayList<Speciality> ()
        val dataArray : JsonArray = JsonParser().parse(jsonStr)
                .getAsJsonObject().getAsJsonArray("data")
        for(data in dataArray) {
            val uid = data.asJsonObject.get("uid").asString
            val name = data.asJsonObject.get("name").asString
            val description = data.asJsonObject.get("description").asString
            val category = data.asJsonObject.get("category").asString
            val actor = data.asJsonObject.get("actor").asString
            val actors = data.asJsonObject.get("actors").asString
            val speciality = Speciality(uid,name,description,category,actor,actors)
            specialityList.add(speciality)
        }
        return specialityList
    }
}