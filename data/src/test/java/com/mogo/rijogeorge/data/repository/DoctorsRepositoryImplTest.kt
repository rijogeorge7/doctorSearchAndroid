package com.mogo.rijogeorge.data.repository

import com.mogo.rijogeorge.data.WebServices.DoctorService
import com.mogo.rijogeorge.data.mock.ProfilesMockJson
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response

class DoctorsRepositoryImplTest {

    //for setting dagger component
    //val doctorsRepository = Mockito.mock(DoctorsRepository::class.java)
    //@InjectMocks lateinit var dataManager: DataManagerImpl

    val doctorService = Mockito.mock(DoctorService::class.java)
    @InjectMocks
    lateinit var repository : DoctorsRepositoryImpl
    inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)
    var callMock = mock<Call<String>>()


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getDoctorsInArea_validParams_doctorList() {

        Mockito.`when`(doctorService.getDoctors(Mockito.anyString(), Mockito.anyInt(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.anyInt()))
                .thenReturn(callMock)
        val response = Response.success(ProfilesMockJson.jsonStr)
        Mockito.`when`(callMock.execute()).thenReturn(response)
        val doctorsList = repository.getDoctorsInArea("37.773,-122.413,100",0,10,null, null)
        Assert.assertEquals(1,doctorsList.size)
    }
}