package com.dnt.demoapp.dagger2.modules.persistence

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.dnt.demoapp.api.DemoAppApi
import com.dnt.demoapp.data.persistence.DemoAppCache
import com.dnt.demoapp.data.persistence.database.DemoAppDatabase
import com.dnt.demoapp.data.persistence.database.dao.ComicsDao
import com.dnt.demoapp.data.repositories.IComicsRepository
import com.dnt.demoapp.data.repositories.ComicsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun provideDemoAppCache(sharedPreferences: SharedPreferences): DemoAppCache =
        DemoAppCache(sharedPreferences)

    @Provides
    @Singleton
    fun provideDatabase(appContext: Context): DemoAppDatabase = Room.databaseBuilder(
        appContext,
        DemoAppDatabase::class.java,
        DemoAppDatabase.DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providesVenueRepository(demoAppApi: DemoAppApi, venueDao: ComicsDao): IComicsRepository = ComicsRepository(demoAppApi, venueDao)

    @Provides
    @Singleton
    fun providesVenueDao(database: DemoAppDatabase): ComicsDao = database.venueDao()

}