package ru.nikitaartamonov.dictionary.data.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.nikitaartamonov.dictionary.ui.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class, ViewModelsBindingModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    val viewModelFactory: MultiViewModelFactory

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}