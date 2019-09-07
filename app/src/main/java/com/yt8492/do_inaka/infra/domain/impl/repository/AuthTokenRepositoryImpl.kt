package com.yt8492.do_inaka.infra.domain.impl.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.yt8492.do_inaka.domain.repository.AuthTokenRepository
import javax.inject.Inject

class AuthTokenRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : AuthTokenRepository {

    override suspend fun getToken(): String? = sharedPreferences.getString(KEY_TOKEN, null)

    override suspend fun saveToken(token: String) {
        sharedPreferences.edit {
            putString(KEY_TOKEN, token)
        }
    }

    companion object {
        private const val KEY_TOKEN = "AuthToken"
    }
}