package com.yt8492.do_inaka.ui

import com.yt8492.do_inaka.ui.login.LoginFragmentModule
import dagger.Module

@Module(
    includes = [
        LoginFragmentModule::class
    ]
)
class UiModule {
}