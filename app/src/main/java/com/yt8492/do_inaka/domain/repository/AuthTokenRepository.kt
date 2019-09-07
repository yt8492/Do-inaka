package com.yt8492.do_inaka.domain.repository

interface AuthTokenRepository {
    suspend fun getToken(): String?
    suspend fun saveToken(token: String)
}