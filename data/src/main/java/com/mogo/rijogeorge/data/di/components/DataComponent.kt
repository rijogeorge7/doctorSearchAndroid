package com.mogo.rijogeorge.data.di.components

import com.mogo.rijogeorge.data.DataManager
import com.mogo.rijogeorge.data.DataManagerImpl
import com.mogo.rijogeorge.data.WebServices.DoctorService
import com.mogo.rijogeorge.data.di.modules.DataModule
import com.mogo.rijogeorge.data.di.modules.NetworkModule
import com.mogo.rijogeorge.data.repository.DoctorsRepositoryImpl
import com.mogo.rijogeorge.data.repository.SpecialityRepositoryImpl
import dagger.Component

@Component(modules = arrayOf(DataModule::class, NetworkModule::class))
interface DataComponent {
    fun inject(DataManager : DataManagerImpl)
    fun inject(DoctorsRepositoryImpl : DoctorsRepositoryImpl)
    fun inject(specialityRepositoryImpl : SpecialityRepositoryImpl)
    fun getDataManager() : DataManager
    fun getDoctorService() : DoctorService
}