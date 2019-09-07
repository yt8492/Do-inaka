package com.yt8492.do_inaka.usecase

import com.yt8492.do_inaka.usecase.checklogin.CheckLoginUseCase
import com.yt8492.do_inaka.usecase.impl.CheckLoginUseCaseImpl
import com.yt8492.do_inaka.usecase.impl.LoginUseCaseImpl
import com.yt8492.do_inaka.usecase.login.LoginUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase = impl

    @Provides
    fun provideCheckLoginUseCase(impl: CheckLoginUseCaseImpl): CheckLoginUseCase = impl
}