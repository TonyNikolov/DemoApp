package com.dnt.demoapp.dagger2

import com.dnt.demoapp.dagger2.modules.ContextModule
import com.dnt.demoapp.dagger2.modules.viewmodel.ViewModelModule
import com.dnt.demoapp.features.MainActivity
import com.dnt.demoapp.dagger2.modules.authentication.AuthenticationModule
import com.dnt.demoapp.dagger2.modules.network.NetworkModule
import com.dnt.demoapp.dagger2.modules.persistence.PersistenceModule
import com.dnt.demoapp.features.comics.ComicsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, NetworkModule::class, PersistenceModule::class, ViewModelModule::class, AuthenticationModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(venuesFragment: ComicsFragment)
}