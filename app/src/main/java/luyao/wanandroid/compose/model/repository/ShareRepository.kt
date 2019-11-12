package luyao.wanandroid.compose.model.repository

import luyao.wanandroid.compose.model.api.BaseRepository
import luyao.wanandroid.compose.model.api.WanRetrofitClient
import luyao.wanandroid.compose.model.bean.Result

/**
 * Created by luyao
 * on 2019/10/15 16:31
 */
class ShareRepository : BaseRepository() {


    suspend fun shareArticle(title: String, url: String): Result<String> {
        return safeApiCall(call = { requestShareArticle(title, url) }, errorMessage = "分享失败")
    }


    private suspend fun requestShareArticle(title: String, url: String): Result<String> =
            executeResponse(WanRetrofitClient.service.shareArticle(title, url))
}