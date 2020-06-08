package com.dnt.demoapp.data.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dnt.demoapp.data.models.Comic
import com.dnt.demoapp.data.persistence.database.dao.ComicsDao

@Database(entities = [Comic::class], version = 1, exportSchema = false)
abstract class DemoAppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "DemoAppDatabase"
    }

    abstract fun venueDao(): ComicsDao
}
