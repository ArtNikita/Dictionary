package ru.nikitaartamonov.dictionary.data.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.nikitaartamonov.dictionary.ui.main.MainActivityViewModel

@Module
interface ViewModelsBindingModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun provideMainViewModel(mainViewModel: MainActivityViewModel): ViewModel
}