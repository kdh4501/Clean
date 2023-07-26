package com.dhkim.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.dhkim.data.model.TextEntity
import io.reactivex.Single

@Dao
interface TextDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTexts(texts: TextEntity): Single<Long>

    @Query("SELECT * FROM text ORDER BY id DESC")
    fun getAllTexts(): Flow<List<TextEntity>>

    @Query("SELECT * FROM text WHERE content LIKE '%' || :content || '%' ORDER BY id DESC")
    fun getTextsByContent(content: String): Flow<List<TextEntity>>

    @Delete
    fun delete(texts: TextEntity)

    @Query("DELETE FROM text")
    fun deleteAllTexts()
}
