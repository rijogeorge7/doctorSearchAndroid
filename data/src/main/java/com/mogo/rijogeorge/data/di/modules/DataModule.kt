package com.mogo.rijogeorge.data.di.modules

import com.mogo.rijogeorge.data.WebServices.DoctorService
import com.mogo.rijogeorge.data.repository.DoctorsRepository
import com.mogo.rijogeorge.data.repository.DoctorsRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DataModule {
    @Provides
    internal fun doctorService(retrofit: Retrofit): DoctorService {
        return retrofit.create(DoctorService::class.java)
    }

    @Provides
    internal fun provideDoctorsRepository(doctorsRepositoryImpl: DoctorsRepositoryImpl): DoctorsRepository {
        return doctorsRepositoryImpl
    }
}