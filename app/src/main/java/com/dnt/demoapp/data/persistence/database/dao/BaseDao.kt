package com.dnt.demoapp.data.persistence.database.dao

import androidx.room.*

/**
 * Base DAO interface to manage common DAO methods.
 */
@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: List<T>): Array<Long>

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)
}
