package com.mogo.rijogeorge.data.di.components

import com.mogo.rijogeorge.data.DataManagerImpl
import com.mogo.rijogeorge.data.di.modules.DataModule
import com.mogo.rijogeorge.data.di.modules.NetworkModule
import com.mogo.rijogeorge.data.repository.DoctorsRepositoryImpl
import dagger.Component

@Component(modules = arrayOf(DataModule::class, NetworkModule::class))
interface DataComponent {
    fun inject(DataManager : DataManagerImpl)
    fun inject(DoctorsRepositoryImpl : DoctorsRepositoryImpl)
}