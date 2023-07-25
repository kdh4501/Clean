package com.dhkim.data.datasource

import com.dhkim.data.model.TextEntity
import kotlinx.coroutines.flow.Flow

interface TextLocalDataSource {
    fun insertText(text: TextEntity): Long
    fun getAllTexts(): Flow<List<TextEntity>>
    fun getSearchTexts(content: String): Flow<List<TextEntity>>
    fun delete(text: TextEntity)
    fun deleteAllTexts()
}