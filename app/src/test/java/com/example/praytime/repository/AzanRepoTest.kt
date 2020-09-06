package com.example.praytime.repository

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.prayertime.data.DataPrayerTimes
import com.prayertime.repository.AzanRepo
import com.prayertime.repository.AzanRepoImpl
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

private lateinit var SUT: AzanRepo
//private lateinit var resp: DataPrayerTimes
//private lateinit var result: List<DataPrayerTimes>

@RunWith(Enclosed::class)
class AzanRepoTest{

    class Azan{
        @Before
        fun `set up`(){
            SUT = AzanRepoImpl()
        }

        @Test
        open fun `on success content is returned`(){

            //Arrange
            val resp : List<DataPrayerTimes> = arrayListOf(mock())

            val result: List<DataPrayerTimes> = mock{
                on {SUT.getPrayerInformation(doubleVar, doubleVar) } doReturn resp
            }

//            whenever(SUT.getPrayerInformation(doubleVar, doubleVar)).thenReturn(result)

            //Act
            SUT.getPrayerInformation(doubleVar, doubleVar)

            //Assert
            verify(SUT).getPrayerInformation(doubleVar, doubleVar)
        }

    }
    companion object {
        private const val doubleVar = 0.0
    }
}