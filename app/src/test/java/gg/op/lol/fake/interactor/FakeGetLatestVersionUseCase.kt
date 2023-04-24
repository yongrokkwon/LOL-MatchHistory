package gg.op.lol.fake.interactor

import gg.op.lol.domain.interactor.GetLatestVersionBaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetLatestVersionUseCase : GetLatestVersionBaseUseCase {
    override suspend fun invoke(): Flow<String> = flow {
        emit("13.8.1")
    }
}
