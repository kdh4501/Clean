package com.dhkim.domain.usecase

import com.dhkim.domain.model.TextItem
import com.dhkim.domain.repository.TextRepository
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

class TextUseCase(private val textRepo: TextRepository) {
    fun getAllLocalTexts(): Flow<List<TextItem>> = textRepo.getAllLocalTexts()
    fun getSearchTexts(query: String): Flow<List<TextItem>> = textRepo.getLocalSearchTexts(query)
    fun insertText(textItem: TextItem): Single<Long> = textRepo.insertText(textItem)
    fun deleteText(textItem: TextItem) = textRepo.deleteText(textItem)
}