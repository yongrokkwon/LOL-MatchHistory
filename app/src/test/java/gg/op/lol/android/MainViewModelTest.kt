package gg.op.lol.android

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import gg.lol.android.ui.main.MainViewModel
import gg.lol.android.util.PreferencesHelper
import gg.op.lol.data.local.dao.GameDataDao
import gg.op.lol.domain.interactor.DeleteGameDataUseCase
import gg.op.lol.domain.interactor.GetChampionsUseCase
import gg.op.lol.domain.interactor.GetItemUseCase
import gg.op.lol.domain.interactor.GetLatestVersionUseCase
import gg.op.lol.domain.interactor.GetRuneUseCase
import gg.op.lol.domain.interactor.GetSpellUseCase
import gg.op.lol.domain.interactor.InsertGameDataUseCase
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@Config(application = HiltTestApplication::class)
@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getLatestVersionUseCase: GetLatestVersionUseCase

    @Inject
    lateinit var deleteGameDataBaseUseCase: DeleteGameDataUseCase

    @Inject
    lateinit var insertBaseDataBaseUseCase: InsertGameDataUseCase

    @Inject
    lateinit var getChampionsUseCase: GetChampionsUseCase

    @Inject
    lateinit var getSpellUseCase: GetSpellUseCase

    @Inject
    lateinit var getRuneUseCase: GetRuneUseCase

    @Inject
    lateinit var getItemUseCase: GetItemUseCase

    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    @Inject
    lateinit var gameDataDao: GameDataDao

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        hiltRule.inject()
    }

    @Test
    fun testMainViewModelInitializationShouldInitializeGameDataDao() {
        // When
        MainViewModel(
            getLatestVersionUseCase,
            deleteGameDataBaseUseCase,
            insertBaseDataBaseUseCase,
            getChampionsUseCase,
            getSpellUseCase,
            getRuneUseCase,
            getItemUseCase,
            preferencesHelper
        )

        runBlocking {
            withContext(Dispatchers.IO) {
                Assert.assertTrue(gameDataDao.getChampions().isEmpty())
                Assert.assertTrue(gameDataDao.getItems().isEmpty())
                Assert.assertTrue(gameDataDao.getRunes().isEmpty())
                Assert.assertTrue(gameDataDao.getSpells().isEmpty())
            }
        }
    }
}
