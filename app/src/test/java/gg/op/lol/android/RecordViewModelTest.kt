package gg.op.lol.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import gg.lol.android.data.search.SearchHistory
import gg.lol.android.data.search.SearchHistoryDao
import gg.lol.android.data.summoner.Summoner
import gg.lol.android.data.summoner.SummonerDao
import gg.lol.android.network.UserService
import gg.lol.android.network.response.SummonerResponse
import gg.lol.android.repository.SearchHistoryRepository
import gg.lol.android.repository.SummonerRepository
import gg.lol.android.ui.record.RecordViewModel
import io.mockk.mockk
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response

class RecordViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var summonerRepository: SummonerRepository
    private lateinit var searchHistoryRepository: SearchHistoryRepository
    private lateinit var userService: UserService
    private lateinit var viewModel: RecordViewModel

    @Before
    fun setUp() {
        userService = mock(UserService::class.java)
        searchHistoryRepository = mockk()
        summonerRepository = SummonerRepository(
            object : SummonerDao {
                override fun insert(summoner: Summoner) {

                }

                override fun fetchSummoners(): List<Summoner> {
                    return emptyList()
                }

                override fun getSummonerByNickName(nickName: String): Summoner? {
                    return null
                }

                override fun update(summoner: Summoner) {

                }

                override fun delete(summoner: Summoner) {

                }
            },
            userService
        )
        viewModel = RecordViewModel(SearchHistoryRepository(object : SearchHistoryDao {
            override fun getSearchHistory(): Flow<List<SearchHistory>> {
                return MutableStateFlow(emptyList())
            }

            override suspend fun insertSearchHistory(searchHistory: SearchHistory) {

            }

            override suspend fun clearSearchHistory() {

            }
        }), summonerRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getSummoner_success() = runTest {
        val testResult = Response.success(
            SummonerResponse(
                code = 200,
                message = "Success",
                result = SummonerResponse.Result(
                    leaguePoints = "647",
                    wins = "157",
                    losses = "139",
                    name = "hide on bush",
                    queueType = "",
                    profileIconId = 6,
                    summonerLevel = 643,
                    tier = "GRANDMASTER"
                )
            )
        )

        // Given
        `when`(userService.getSummoner("hide on bush")).thenReturn(
            Response.success(
                SummonerResponse(
                    code = 200,
                    message = "Success",
                    result = SummonerResponse.Result(
                        leaguePoints = "647",
                        wins = "157",
                        losses = "139",
                        name = "hide on bush",
                        queueType = "",
                        profileIconId = 6,
                        summonerLevel = 643,
                        tier = "GRANDMASTER"
                    )
                )
            )
        )

        // When
        viewModel.getSummoner("hide on bush")

        // Then
        val result = viewModel.summonerResponse.getOrAwaitValue()
        assertThat(result, IsEqual(testResult.body()))
    }

//    @Test
//    fun fetchUsers_error() = runBlockingTest {
//        val exception = IOException("Failed to fetch users")
//        every { summonerRepository.getSummoner("Hide On Bush") } returns Result.Error(exception)
//
//        viewModel.getSummoner()
//
//        assertThat(viewModel.summonerResponse.value).isNull()
//    }

    /**
     * Gets the value of a [LiveData] or waits for it to have one, with a timeout.
     *
     * Use this extension from host-side (JVM) tests. It's recommended to use it alongside
     * `InstantTaskExecutorRule` or a similar mechanism to execute tasks synchronously.
     */
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
        afterObserve: () -> Unit = {}
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data = o
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)

        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            this.removeObserver(observer)
            throw TimeoutException("LiveData value was never set.")
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }
}

