package gg.op.lol.android

import gg.lol.android.ui.main.MainViewModel
import gg.lol.android.util.PreferencesHelper
import gg.op.lol.domain.interactor.DeleteGameDataUseCase
import gg.op.lol.domain.interactor.GetChampionsUseCase
import gg.op.lol.domain.interactor.GetItemUseCase
import gg.op.lol.domain.interactor.GetLatestVersionUseCase
import gg.op.lol.domain.interactor.GetRuneUseCase
import gg.op.lol.domain.interactor.GetSpellUseCase
import gg.op.lol.domain.interactor.InsertGameDataUseCase
import gg.op.lol.domain.models.Champion
import gg.op.lol.domain.models.ChampionRuneItemSpell
import gg.op.lol.domain.models.Item
import gg.op.lol.domain.models.Rune
import gg.op.lol.domain.models.Spell
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

// TODO 버전 가져오기 수정, 테스트 케이스 추가
@ExperimentalCoroutinesApi
class MainViewModelTest {
    private val getLatestVersionUseCase: GetLatestVersionUseCase = mockk()
    private val deleteGameDataBaseUseCase: DeleteGameDataUseCase = mockk()
    private val insertBaseDataBaseUseCase: InsertGameDataUseCase = mockk()
    private val getChampionsUseCase: GetChampionsUseCase = mockk()
    private val getSpellUseCase: GetSpellUseCase = mockk()
    private val getRuneUseCase: GetRuneUseCase = mockk()
    private val getItemUseCase: GetItemUseCase = mockk()
    private val preferencesHelper: PreferencesHelper = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @Test
    fun `loadGameDataFromApi success`() = runBlockingTest {
        // Given
        val lolApiCurrentVersion = ""
        val lolApiLatestVersion = "11.9.1"
        val versionPair = Pair(lolApiCurrentVersion, lolApiLatestVersion)

        val champions = listOf<Champion>()
        val runes = listOf<Rune>()
        val items = listOf<Item>()
        val spells = listOf<Spell>()

        every { preferencesHelper.lolApiVersion = lolApiLatestVersion } just Runs
        every { preferencesHelper.lolApiVersion } returns lolApiCurrentVersion
        coEvery { getLatestVersionUseCase.invoke(Unit) } returns flowOf(lolApiLatestVersion)
        coEvery { getChampionsUseCase.invoke(versionPair) } returns champions
        coEvery { getSpellUseCase.invoke(versionPair) } returns spells
        coEvery { getRuneUseCase.invoke(versionPair) } returns runes
        coEvery { getItemUseCase.invoke(versionPair) } returns items
        coEvery { deleteGameDataBaseUseCase.invoke(Unit) } just Runs
        coEvery { insertBaseDataBaseUseCase.invoke(any()) } just Runs

        // When
        val viewModel = MainViewModel(
            getLatestVersionUseCase,
            deleteGameDataBaseUseCase,
            insertBaseDataBaseUseCase,
            getChampionsUseCase,
            getSpellUseCase,
            getRuneUseCase,
            getItemUseCase,
            preferencesHelper
        )
//        viewModel.loadGameDataFromApi(lolApiLatestVersion)

        // Then
        coVerifyOrder {
            preferencesHelper.lolApiVersion
            getLatestVersionUseCase.invoke(Unit)
            getChampionsUseCase.invoke(versionPair)
            getSpellUseCase.invoke(versionPair)
            getRuneUseCase.invoke(versionPair)
            getItemUseCase.invoke(versionPair)
            deleteGameDataBaseUseCase.invoke(Unit)
            insertBaseDataBaseUseCase.invoke(
                ChampionRuneItemSpell(
                    champions,
                    runes,
                    items,
                    spells
                )
            )
            preferencesHelper.lolApiVersion = lolApiLatestVersion
        }
    }
}
