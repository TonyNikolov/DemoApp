package com.dnt.demoapp.dagger2.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dnt.demoapp.features.comics.ComicsViewModel
import com.dnt.demoapp.dagger2.viewmodels.ViewModelFactory
import com.dnt.demoapp.dagger2.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ComicsViewModel::class)
    internal abstract fun bindComicsViewModel(comicsViewModel: ComicsViewModel): ViewModel
}
