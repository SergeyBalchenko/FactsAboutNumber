package com.example.factsaboutnumber.di

import android.content.Context
import com.example.factsaboutnumber.di.module.DbModule
import com.example.factsaboutnumber.di.module.NetworkModule
import com.example.factsaboutnumber.di.module.RepositoryModule
import com.example.factsaboutnumber.di.module.UseCaseModule
import com.example.factsaboutnumber.screen.chooseNumber.ChooseNumberFragment
import com.example.factsaboutnumber.screen.infoNumber.InfoNumberFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        DbModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(fragment: ChooseNumberFragment)
    fun inject(fragment: InfoNumberFragment)
}