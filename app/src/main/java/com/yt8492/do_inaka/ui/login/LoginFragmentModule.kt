package com.yt8492.do_inaka.ui.login

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginFragmentModule {

    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment
}