package com.example.quotesapp

import android.app.Application
import com.example.quotesapp.di.quoteAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import java.util.logging.Level

class QuoteApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Koin
        startKoin {
            androidLogger()
            androidContext(this@QuoteApplication)
            modules( quoteAppModule )

        }
    }

}