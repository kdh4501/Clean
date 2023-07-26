package com.dhkim.clean.di

import com.dhkim.data.repository.TextRepositoryImpl
import com.dhkim.domain.repository.TextRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<TextRepository> { TextRepositoryImpl(get()) }
}