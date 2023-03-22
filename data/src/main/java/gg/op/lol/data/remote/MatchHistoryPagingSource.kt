package gg.op.lol.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import gg.op.lol.data.remote.api.MatchService
import gg.op.lol.data.remote.mapper.MatchHistoryMapper
import gg.op.lol.domain.models.MatchHistory

const val STARTING_PAGE_INDEX = 0
const val PAGE_COUNT = 5

class MatchHistoryPagingSource(
    private val matchService: MatchService,
    private val matchHistoryMapper: MatchHistoryMapper,
    private val puuid: String?
) : PagingSource<Int, MatchHistory>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MatchHistory> {
        Log.d("##", "load call")
        val page = params.key ?: STARTING_PAGE_INDEX

        if (puuid == null) throw NullPointerException("puuid can not be null")
        return try {
            val matchIds = matchService.getMatches(puuid, page, PAGE_COUNT)
            val matchHistoryResponse = matchIds.map { matchService.getMatch(it) }
            val result = matchHistoryResponse.map { matchHistoryMapper.mapFromLocal(it) }
            LoadResult.Page(
                data = result,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - PAGE_COUNT,
                nextKey = if (page == result.size) null else page + PAGE_COUNT
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MatchHistory>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
