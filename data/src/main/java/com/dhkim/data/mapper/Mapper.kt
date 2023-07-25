package com.dhkim.data.mapper

import com.dhkim.data.model.TextEntity
import com.dhkim.domain.model.TextItem

// data -> domain
fun mapperToText(texts: List<TextEntity>): List<TextItem> {
    return texts.toList().map {
        TextItem(
            it.id,
            it.time,
            it.content
        )
    }
}
fun TextEntity.map() = TextItem(
    id,
    time,
    content
)

// domain -> data
fun mapperToTextEntity(textItems: List<TextItem>): List<TextEntity> {
    return textItems.toList().map {
        TextEntity(
            it.id,
            it.time,
            it.content
        )
    }
}
fun TextItem.map() = TextEntity(
    id,
    time,
    content
)