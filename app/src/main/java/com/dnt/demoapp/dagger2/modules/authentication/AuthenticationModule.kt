package com.dnt.demoapp.dagger2.modules.authentication

import com.dnt.demoapp.dagger2.modules.authentication.IAuthenticationModule
import com.dnt.demoapp.data.repositories.AuthenticationRepository
import com.dnt.demoapp.data.repositories.IAuthenticationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthenticationModule : IAuthenticationModule {
    @Singleton
    @Provides
    override fun provideAuthenticationRepository(): IAuthenticationRepository {
        return AuthenticationRepository()
    }
}