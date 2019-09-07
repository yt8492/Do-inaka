package com.yt8492.do_inaka.usecase.checklogin

interface CheckLoginUseCase {
    suspend fun execute(): CheckLoginResult
}