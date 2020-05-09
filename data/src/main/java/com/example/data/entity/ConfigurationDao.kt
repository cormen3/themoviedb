package com.example.data.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.model.local.ConfigurationEntity
import io.reactivex.Completable
import io.reactivex.Flowable


@Dao
interface ConfigurationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(configuration: ConfigurationEntity): Completable

    @Query("SELECT * FROM configuration LIMIT 1")
    fun select(): Flowable<ConfigurationEntity?>

    @Query("SELECT COUNT(*) FROM configuration")
    fun count(): Flowable<Int>

    @Query("DELETE FROM configuration")
    fun remove(): Completable
}
