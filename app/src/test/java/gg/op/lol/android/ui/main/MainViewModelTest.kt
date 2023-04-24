package gg.op.lol.android.ui.main

import gg.lol.android.ui.main.MainViewModel
import gg.op.lol.domain.interactor.DeleteGameDataBaseUseCase
import gg.op.lol.domain.interactor.GetChampionsBaseUseCase
import gg.op.lol.domain.interactor.GetItemBaseUseCase
import gg.op.lol.domain.interactor.GetRuneBaseUseCase
import gg.op.lol.domain.interactor.GetSpellBaseUseCase
import gg.op.lol.domain.interactor.InsertGameDataBaseUseCase
import gg.op.lol.fake.interactor.FakeGetLatestVersionUseCase
import gg.op.lol.fake.util.FakePreferencesHelperImp
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk

class MainViewModelTest : ShouldSpec({
    val fakeGetLatestVersionUseCase = FakeGetLatestVersionUseCase()
    val fakeDeleteGameDataUseCase = mockk<DeleteGameDataBaseUseCase>()
    val fakeInsertGameDataBaseUseCase = mockk<InsertGameDataBaseUseCase>()
    val fakeGetChampionsUseCase = mockk<GetChampionsBaseUseCase>()
    val fakeGetSpellUseCase = mockk<GetSpellBaseUseCase>()
    val fakeGetRuneUseCase = mockk<GetRuneBaseUseCase>()
    val fakeGetItemUseCase = mockk<GetItemBaseUseCase>()
    val fakePreferencesHelperImp = FakePreferencesHelperImp()

    coEvery { fakeDeleteGameDataUseCase.invoke() } just Runs
    coEvery { fakeInsertGameDataBaseUseCase.invoke(any()) } just Runs
    coEvery { fakeGetChampionsUseCase.invoke(any()) } returns listOf()
    coEvery { fakeGetSpellUseCase.invoke(any()) } returns listOf()
    coEvery { fakeGetRuneUseCase.invoke(any()) } returns listOf()
    coEvery { fakeGetItemUseCase.invoke(any()) } returns listOf()

    MainViewModel(
        fakeGetLatestVersionUseCase,
        fakeDeleteGameDataUseCase,
        fakeInsertGameDataBaseUseCase,
        fakeGetChampionsUseCase,
        fakeGetSpellUseCase,
        fakeGetRuneUseCase,
        fakeGetItemUseCase,
        fakePreferencesHelperImp
    )

    should("initialize GameDataDao") {
        coVerify(exactly = 1) {
            fakeGetChampionsUseCase.invoke(any())
            fakeGetSpellUseCase.invoke(any())
            fakeGetRuneUseCase.invoke(any())
            fakeGetItemUseCase.invoke(any())

            fakeDeleteGameDataUseCase.invoke()
            fakeInsertGameDataBaseUseCase.invoke(any())
        }

        fakePreferencesHelperImp.lolApiVersion shouldBe "13.8.1"
    }
})
