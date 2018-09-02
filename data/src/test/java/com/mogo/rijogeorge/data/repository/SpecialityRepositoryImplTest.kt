package com.mogo.rijogeorge.data.repository

import com.mogo.rijogeorge.data.WebServices.DoctorService
import com.mogo.rijogeorge.data.mock.SpecialityMockJson
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response

class SpecialityRepositoryImplTest {

    val doctorService = Mockito.mock(DoctorService::class.java)
    @InjectMocks
    lateinit var repository : SpecialityRepositoryImpl
    inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)
    var callMock = mock<Call<String>>()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getAvailableSpecialities() {
        Mockito.`when`(doctorService.getAvailableSpecialities(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(callMock)
        val response = Response.success(SpecialityMockJson.jsonStr)
        Mockito.`when`(callMock.execute()).thenReturn(response)
        val specialityList = repository.getAvailableSpecialities("",0,100)
        Assert.assertEquals(1,specialityList.size)
    }
}