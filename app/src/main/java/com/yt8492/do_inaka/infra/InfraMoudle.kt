package com.yt8492.do_inaka.infra

import android.content.Context
import android.content.SharedPreferences
import com.yt8492.do_inaka.domain.repository.LoginUserRepository
import com.yt8492.do_inaka.domain.repository.DriverRepository
import com.yt8492.do_inaka.domain.repository.LoginRepository
import com.yt8492.do_inaka.domain.repository.RequesterRepository
import com.yt8492.do_inaka.infra.domain.impl.repository.LoginUserRepositoryImpl
import com.yt8492.do_inaka.infra.mock.MockDriverRepository
import com.yt8492.do_inaka.infra.mock.MockLoginRepository
import com.yt8492.do_inaka.infra.mock.MockRequesterRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InfraMoudle {

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideAuthTokenRepository(impl: LoginUserRepositoryImpl): LoginUserRepository = impl

    @Singleton
    @Provides
    fun provideLoginRepository(): LoginRepository = MockLoginRepository

    @Singleton
    @Provides
    fun provideDriverRepository(): DriverRepository = MockDriverRepository

    @Singleton
    @Provides
    fun provideRequesterRepository(): RequesterRepository = MockRequesterRepository

    companion object {
        private const val PREF_NAME = "Do-inaka"
    }
}