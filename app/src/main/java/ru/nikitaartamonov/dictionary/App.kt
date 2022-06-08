package ru.nikitaartamonov.dictionary

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.nikitaartamonov.dictionary.data.di.AppComponent
import ru.nikitaartamonov.dictionary.data.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().app

val Context.viewModelFactory get() = app.appComponent.viewModelFactory
val Fragment.viewModelFactory get() = app.appComponent.viewModelFactory