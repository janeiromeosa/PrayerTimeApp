
import android.content.Context
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import com.prayertime.data.DataPrayerTimes
import com.prayertime.repository.AzanRepo
import com.prayertime.view.prayertime.PrayerTimeViewModel
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

lateinit var SUT: PrayerTimeViewModel
lateinit var repo: AzanRepo
lateinit var context: Context
lateinit var data: DataPrayerTimes


@RunWith(Enclosed::class)
class PrayerTimeViewModelTest{

    class viewModel {

        @Before
        fun setUpTtest() {
            repo = mock()
            context = mock()
            data = mock()
            SUT = PrayerTimeViewModel(repo, context)
        }

        @Test
        fun `get prayer times data successfully from repository - should not return null`() {
            //arrange
            val fakeList: List<DataPrayerTimes> = fakeList()
            repo.stub {
                on { getPrayerInformation(0.0,0.0) }.thenReturn(fakeList)
            }
            //act
            SUT.getPrayerTimes()

            //assert
            verify(SUT.getPrayerTimes())
        }

        private fun fakeList(): List<DataPrayerTimes> {
            data.stub {
                on { it.name }.thenReturn("")
                on { it.times }.thenReturn("")
            }

            val list: List<DataPrayerTimes> = listOf(data)
            return list
        }

        @Test
        fun `get location data successfully from geocoder`() {

        }

        @Test
        fun `set location data successfully`() {

        }
    }
}
