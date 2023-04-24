package gg.lol.android.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gg.op.lol.domain.interactor.DeleteGameDataBaseUseCase
import gg.op.lol.domain.interactor.DeleteGameDataUseCase
import gg.op.lol.domain.interactor.GetChampionsBaseUseCase
import gg.op.lol.domain.interactor.GetChampionsUseCase
import gg.op.lol.domain.interactor.GetItemBaseUseCase
import gg.op.lol.domain.interactor.GetItemUseCase
import gg.op.lol.domain.interactor.GetLatestVersionBaseUseCase
import gg.op.lol.domain.interactor.GetLatestVersionUseCase
import gg.op.lol.domain.interactor.GetRuneBaseUseCase
import gg.op.lol.domain.interactor.GetRuneUseCase
import gg.op.lol.domain.interactor.GetSpellBaseUseCase
import gg.op.lol.domain.interactor.GetSpellUseCase
import gg.op.lol.domain.interactor.InsertGameDataBaseUseCase
import gg.op.lol.domain.interactor.InsertGameDataUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetLatestVersionBaseUseCase(
        impl: GetLatestVersionUseCase
    ): GetLatestVersionBaseUseCase

    @Binds
    abstract fun bindDeleteGameDataUseCase(
        impl: DeleteGameDataUseCase
    ): DeleteGameDataBaseUseCase

    @Binds
    abstract fun bindInsertGameDataUseCase(
        impl: InsertGameDataUseCase
    ): InsertGameDataBaseUseCase

    @Binds
    abstract fun bindGetChampionsUseCase(
        impl: GetChampionsUseCase
    ): GetChampionsBaseUseCase

    @Binds
    abstract fun bindGetSpellUseCase(
        impl: GetSpellUseCase
    ): GetSpellBaseUseCase

    @Binds
    abstract fun bindGetRuneUseCase(
        impl: GetRuneUseCase
    ): GetRuneBaseUseCase

    @Binds
    abstract fun bindGetItemUseCase(
        impl: GetItemUseCase
    ): GetItemBaseUseCase
}
