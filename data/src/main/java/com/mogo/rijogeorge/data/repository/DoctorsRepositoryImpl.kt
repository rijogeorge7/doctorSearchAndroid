package com.mogo.rijogeorge.data.repository

import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.mogo.rijogeorge.data.DataManagerImpl
import com.mogo.rijogeorge.data.WebServices.DoctorService
import com.mogo.rijogeorge.data.model.Address
import com.mogo.rijogeorge.data.model.DoctorProfile
import com.mogo.rijogeorge.data.model.Language
import javax.inject.Inject

class DoctorsRepositoryImpl @Inject constructor() : DoctorsRepository {
    init {
        DataManagerImpl.dataComponent.inject(this)
    }

    @Inject
    lateinit var doctorService: DoctorService

    override fun getDoctorsInArea(location: String, skip: Int, limit: Int, specialty_uid: String?, insurance_uid: String?): List<DoctorProfile> {
        var callResponse = doctorService.getDoctors(location,limit,null, null, null, null, null, null, null, null, null, skip)
        var response = callResponse.execute()
        var jsonStr = response.body()
        var doctorProfiles : List<DoctorProfile> = getProfileListFromJson(jsonStr)
        return doctorProfiles
    }

    private fun getProfileListFromJson(jsonStr: String?): List<DoctorProfile> {
        val doctorProfiles = ArrayList<DoctorProfile>()
        val dataArray : JsonArray = JsonParser().parse(jsonStr)
                .getAsJsonObject().getAsJsonArray("data")
        for ( data in dataArray) {
            val practiseArray = data.asJsonObject.getAsJsonArray("practices")
            var address: Address? = null
            var distance: Double? = null
            if(practiseArray.size()>0) {
                val practise = practiseArray[0]
                distance = practise.asJsonObject.get("distance").asDouble
                val visit_address = practise.asJsonObject.get("visit_address")
                val street = visit_address.asJsonObject.get("street").asString
                val city = visit_address.asJsonObject.get("city").asString
                val state = visit_address.asJsonObject.get("state").asString
                val zip = visit_address.asJsonObject.get("zip").asString
                address = Address(street,city,state,zip)
            }
            var profileObj = data.asJsonObject.get("profile")
            val first_name = profileObj.asJsonObject.get("first_name").asString
            val middle_name = profileObj.asJsonObject.get("middle_name")?.asString
            val last_name = profileObj.asJsonObject.get("last_name").asString
            val slug = profileObj.asJsonObject.get("slug")?.asString
            val title = profileObj.asJsonObject.get("title")?.asString
            val image_url = profileObj.asJsonObject.get("image_url")?.asString
            val gender = profileObj.asJsonObject.get("gender")?.asString
            val bio = profileObj.asJsonObject.get("bio")?.asString
            val languagesArray = profileObj.asJsonObject.getAsJsonArray("languages")
            val languages = ArrayList<Language>()
            for(language in languagesArray){
                val languageObj = Language(language.asJsonObject.get("name").asString, language.asJsonObject.get("code").asString)
                languages.add(languageObj)
            }
            var speciality: String? = null
            val specialityArray = data.asJsonObject.getAsJsonArray("specialties")
            if(specialityArray.size()>0){
                speciality = specialityArray[0].asJsonObject.get("name").asString
            }
            val profile = DoctorProfile(languages, address, first_name, title, bio, image_url, middle_name, last_name, gender, slug, distance, speciality)
            doctorProfiles.add(profile)
        }


        return doctorProfiles;
    }

}