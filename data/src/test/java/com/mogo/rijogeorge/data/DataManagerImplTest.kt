package com.mogo.rijogeorge.data

import com.mogo.rijogeorge.data.model.DoctorProfile
import com.mogo.rijogeorge.data.model.Speciality
import com.mogo.rijogeorge.data.repository.DoctorsRepository
import com.mogo.rijogeorge.data.repository.SpecialityRepository
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DataManagerImplTest {

    val doctorsRepository = Mockito.mock(DoctorsRepository::class.java)
    val specialityRepository = Mockito.mock(SpecialityRepository::class.java)
    @InjectMocks
    lateinit var dataManager:  DataManagerImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    fun getDoctorsInArea() {
        Mockito.`when`(doctorsRepository.getDoctorsInArea(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(ArrayList<DoctorProfile>())
        val testSub = TestObserver<List<DoctorProfile>>()
        dataManager.getDoctorsInArea("37.773,-122.413,100",0,10,null, null)
                .subscribe(testSub)
        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertComplete()
    }

    @Test
    fun getSpecialities() {
        Mockito.`when`(specialityRepository.getAvailableSpecialities(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(ArrayList<Speciality>())
        val testSub = TestObserver<List<Speciality>>()
        dataManager.getAvailableSpecialities("",0,10)
                .subscribe(testSub)
        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertComplete()
    }
}