package com.dhkim.clean

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.dhkim.clean.di.repositoryModule
import com.dhkim.clean.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application(){
    init {
        instance = this
    }

    companion object {
        lateinit var instance: MyApplication
        fun applicationContext(): Context {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        startKoin {
            androidContext(this@MyApplication)
            modules(viewModelModule)
            modules(repositoryModule)
        }
    }
}