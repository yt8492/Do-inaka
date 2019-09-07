package com.yt8492.do_inaka

import android.content.Context
import com.yt8492.do_inaka.infra.InfraMoudle
import com.yt8492.do_inaka.ui.UiModule
import com.yt8492.do_inaka.usecase.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        UiModule::class,
        UseCaseModule::class,
        InfraMoudle::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}