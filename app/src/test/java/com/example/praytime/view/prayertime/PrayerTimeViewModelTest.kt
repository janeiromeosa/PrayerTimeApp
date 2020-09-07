import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.prayertime.data.DataPrayerTimes
import com.prayertime.repository.AzanRepo
import com.prayertime.repository.AzanRepoImpl
import com.prayertime.view.prayertime.PrayerTimeViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PrayerTimeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var SUT: PrayerTimeViewModel

    lateinit var repo: AzanRepo
    lateinit var geocoder: Geocoder
    lateinit var location: Location
    lateinit var address: Address

    @Before
    fun setUpTtest() {
        repo = mock<AzanRepoImpl>()
        geocoder = mock()
        location = mock()
        address = mock()
        SUT = PrayerTimeViewModel(repo, geocoder)
        SUT.getLocationObservable().value = location
    }

    @Test
    fun `get prayer times data successfully from repository - should not return null`() {
        //arrange
        whenever(repo.getPrayerInformation(0.0, 0.0)).thenReturn(
            fakeList()
        )

        //act
        SUT.getPrayerTimes()

        //assert
        assert(SUT.getPrayerTimesObservable().value != null)
    }

    @Test
    fun `get prayer times data successfully from repository - should return null`() {
        //arrange
        SUT.getLocationObservable().value = location
        whenever(repo.getPrayerInformation(0.0, 0.0)).thenReturn(
            null
        )

        //act
        SUT.getPrayerTimes()

        //assert
        assert(SUT.getPrayerTimesObservable().value == null)
    }

    @Test
    fun `get address data successfully from geocoder - should not return null`() {
        //arrange
        var listAddress = mutableListOf<Address>(
            address
        )

        SUT.getCountryInformationObservable().value = listAddress

        whenever(geocoder.getFromLocation(0.0, 0.0, 1)).thenReturn(
            fakeAddressList()
        )

        //act
        SUT.getAddress()

        //assert
        assert(SUT.getCountryInformationObservable().value != null)
    }



    @Test
    fun `set location data successfully`() {//arrange
        val listAddress = mutableListOf<Address>(
            address
        )

        SUT.getCountryInformationObservable().value = listAddress

        whenever(geocoder.getFromLocation(0.0, 0.0, 1)).thenReturn(
            null
        )

        //act
        SUT.getAddress()

        //assert
        assert(SUT.getCountryInformationObservable().value == null)
    }

    private fun fakeList(): List<DataPrayerTimes> {

        val data = DataPrayerTimes("", "")

        return mutableListOf<DataPrayerTimes>(
            data
        )
    }

    private fun fakeAddressList(): List<Address>? {
        val address = SUT.getCountryInformationObservable().value

        return address

    }
}
