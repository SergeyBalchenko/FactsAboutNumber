package com.example.factsaboutnumber.di

import android.content.Context
import com.example.factsaboutnumber.di.module.DbModule
import com.example.factsaboutnumber.di.module.NetworkModule
import com.example.factsaboutnumber.di.module.RepositoryModule
import com.example.factsaboutnumber.di.module.UseCaseModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        NetworkModule::class,
        DbModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}