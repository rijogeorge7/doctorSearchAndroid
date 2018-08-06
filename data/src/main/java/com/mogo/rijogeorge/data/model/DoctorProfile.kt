package com.mogo.rijogeorge.data.model

class DoctorProfile {
    var languages: ArrayList<Language>? = null

    var first_name: String? = null

    var title: String? = null

    var bio: String? = null

    var image_url: String? = null

    var middle_name: String? = null

    var last_name: String? = null

    var gender: String? = null

    var slug: String? = null

    override fun toString(): String {
        return "ClassPojo [languages = $languages, first_name = $first_name, title = $title, bio = $bio, image_url = $image_url, middle_name = $middle_name, last_name = $last_name, gender = $gender, slug = $slug]"
    }
}
