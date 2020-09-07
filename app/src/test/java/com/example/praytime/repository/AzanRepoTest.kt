package com.example.praytime.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.prayertime.data.DataPrayerTimes
import com.prayertime.repository.AzanRepo
import com.prayertime.repository.AzanRepoImpl
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class AzanRepoTest {
    private lateinit var SUT: AzanRepo


    @Before
    fun `set up`() {
        SUT = mock<AzanRepoImpl>()
    }

    @Test
    fun `on success content is returned`() {
        //Arrange
        whenever(SUT.getPrayerInformation(doubleVar, doubleVar)).thenReturn(fakeListOfData())

        //Act
        val list = SUT.getPrayerInformation(doubleVar, doubleVar)

        //Assert
//       assert(list!=null)
        assertEquals(list, fakeListOfData())
    }

    private fun fakeListOfData(): List<DataPrayerTimes>? {
        val data = DataPrayerTimes("", "")
        return mutableListOf(
            data
        )
    }


    companion object {
        private const val doubleVar = 0.0
    }
}