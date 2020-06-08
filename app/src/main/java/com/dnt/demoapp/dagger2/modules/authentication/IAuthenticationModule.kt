package com.dnt.demoapp.dagger2.modules.authentication

import com.dnt.demoapp.data.repositories.IAuthenticationRepository

interface IAuthenticationModule {
    fun provideAuthenticationRepository(): IAuthenticationRepository
}