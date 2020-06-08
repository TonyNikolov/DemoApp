package com.dnt.demoapp.data.persistence.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.dnt.demoapp.data.models.Comic

@Dao
abstract class ComicsDao : BaseDao<Comic> {

    @Query("SELECT * FROM comic")
    abstract fun getAll(): List<Comic>
}
