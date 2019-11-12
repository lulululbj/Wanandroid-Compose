package luyao.wanandroid.compose.model.repository

import com.google.gson.Gson
import luyao.wanandroid.compose.App
import luyao.wanandroid.compose.model.api.BaseRepository
import luyao.wanandroid.compose.model.api.WanRetrofitClient
import luyao.wanandroid.compose.model.bean.Result
import luyao.wanandroid.compose.model.bean.User

/**
 * Created by luyao
 * on 2019/4/10 9:42
 */
class LoginRepository : BaseRepository() {

//    private var isLogin by Preference(Preference.IS_LOGIN, false)
//    private var userJson by Preference(Preference.USER_GSON, "")


    suspend fun login(userName: String, passWord: String): Result<User> {
        return safeApiCall(call = { requestLogin(userName, passWord) },
                errorMessage = "登录失败!")
    }

    // TODO Move into DataSource Layer ?
    private suspend fun requestLogin(userName: String, passWord: String): Result<User> {
        val response = WanRetrofitClient.service.login(userName, passWord)

        return executeResponse(response, {
            val user = response.data
//            isLogin = true
//            userJson = Gson().toJson(user)
            App.CURRENT_USER = user
        })
    }

    suspend fun register(userName: String, passWord: String): Result<User> {
        return safeApiCall(call = { requestRegister(userName, passWord) }, errorMessage = "注册失败")
    }

    private suspend fun requestRegister(userName: String, passWord: String): Result<User> {
        val response = WanRetrofitClient.service.register(userName, passWord, passWord)
        return executeResponse(response, { requestLogin(userName, passWord) })
    }

}