package com.dhkim.data.repository

import com.dhkim.data.datasource.TextLocalDataSource
import com.dhkim.data.mapper.map
import com.dhkim.data.mapper.mapperToText
import com.dhkim.domain.model.TextItem
import com.dhkim.domain.repository.TextRepository
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TextRepositoryImpl(private val textLocalDataSource: TextLocalDataSource): TextRepository {
    override fun getAllLocalTexts(): Flow<List<TextItem>> {
        return textLocalDataSource.getAllTexts().map { localTexts -> mapperToText(localTexts) }
    }

    override fun getLocalSearchTexts(query: String): Flow<List<TextItem>> {
        return textLocalDataSource.getSearchTexts(query).map { localTexts -> mapperToText(localTexts) }
    }

    override fun insertText(textItem: TextItem): Single<Long> {
        return textLocalDataSource.insertText(textItem.map())
    }

    override fun deleteText(textItem: TextItem) {
        textLocalDataSource.delete(textItem.map())
    }

    override fun deleteAllText() {
        textLocalDataSource.deleteAllTexts()
    }
}