package com.dhkim.data.datasource

import com.dhkim.data.dao.TextDao
import com.dhkim.data.model.TextEntity
import kotlinx.coroutines.flow.Flow

class TextLocalDataSourceImpl(private val textDao: TextDao): TextLocalDataSource {
    override fun insertText(text: TextEntity): Long {
        return textDao.insertTexts(text)
    }

    override fun getAllTexts(): Flow<List<TextEntity>> {
        return textDao.getAllTexts()
    }

    override fun getSearchTexts(content: String): Flow<List<TextEntity>> {
        return textDao.getTextsByContent(content)
    }

    override fun delete(text: TextEntity) {
        return textDao.delete(text)
    }

    override fun deleteAllTexts() {
        return textDao.deleteAllTexts()
    }
}