package luyao.wanandroid.compose.model.repository

import luyao.wanandroid.compose.model.api.BaseRepository
import luyao.wanandroid.compose.model.api.WanRetrofitClient
import luyao.wanandroid.compose.model.bean.ArticleList
import luyao.wanandroid.compose.model.bean.Result
import luyao.wanandroid.compose.model.bean.SystemParent

/**
 * Created by luyao
 * on 2019/4/10 14:34
 */
class SystemRepository : BaseRepository() {

    suspend fun getSystemTypeDetail(cid: Int, page: Int): Result<ArticleList> {
        return safeApiCall(call = {requestSystemTypeDetail(cid, page)},errorMessage = "网络错误")
    }

    suspend fun getSystemTypes(): Result<List<SystemParent>> {
        return safeApiCall(call = { requestSystemTypes() }, errorMessage = "网络错误")
    }

    suspend fun getBlogArticle(cid: Int, page: Int): Result<ArticleList> {
        return safeApiCall(call = {requestBlogArticle(cid, page)},errorMessage = "网络错误")
    }

    private suspend fun requestSystemTypes(): Result<List<SystemParent>> =
            executeResponse(WanRetrofitClient.service.getSystemType())

    private suspend fun requestSystemTypeDetail(cid: Int,page: Int): Result<ArticleList> =
            executeResponse(WanRetrofitClient.service.getSystemTypeDetail(page, cid) )

    private suspend fun requestBlogArticle(cid: Int,page: Int): Result<ArticleList> =
            executeResponse(WanRetrofitClient.service.getSystemTypeDetail(page, cid) )
}