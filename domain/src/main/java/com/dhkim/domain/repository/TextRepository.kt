package com.dhkim.domain.repository

import com.dhkim.domain.model.TextItem
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface TextRepository {
    fun getAllLocalTexts(): Flow<List<TextItem>>
    fun getLocalSearchTexts(query: String): Flow<List<TextItem>>
    fun insertText(textItem: TextItem): Single<Long>
    fun deleteText(textItem: TextItem)
    fun deleteAllText()

}