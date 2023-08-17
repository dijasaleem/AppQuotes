package com.example.quotesapp.di
import androidx.room.Room
import com.example.quotesapp.data.QuoteRepository
import com.example.quotesapp.data.local.QuoteDatabase
import com.example.quotesapp.data.remote.QuotesApi
import com.example.quotesapp.domain.AddToFavUseCase
import com.example.quotesapp.domain.GetFavoriteQuotesUseCase
import com.example.quotesapp.domain.GetQuotesUseCase
import com.example.quotesapp.ui.theme.viewModels.DisplayDetailViewModel
import com.example.quotesapp.ui.theme.viewModels.DisplayFavViewModel
import com.example.quotesapp.ui.theme.viewModels.DisplayQuoteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val quoteAppModule: Module  get() = module {
    includes(
        viewModelModule,
        domainModule,
        retrofitModule,
        apiModule,
        databaseModule,
        repositoryModule
    )
}

val viewModelModule = module{
    viewModel { DisplayDetailViewModel( get() ) }
    viewModel { DisplayFavViewModel( get() ) }
    viewModel { DisplayQuoteViewModel( get() ) }
}
val domainModule = module{
    factory { AddToFavUseCase( get() ) }
    factory { GetFavoriteQuotesUseCase( get() ) }
    factory { GetQuotesUseCase( get() ) }
}

val retrofitModule = module {
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://type.fit/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    single { provideRetrofit() }
}

val apiModule = module {
    fun provideQuoteApi(retrofit: Retrofit) : QuotesApi{
        return retrofit.create(QuotesApi::class.java)
    }
    single { provideQuoteApi(get()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            QuoteDatabase::class.java,
            "quoteDB"
        ).build()
    }
    single { get<QuoteDatabase>().quoteDao() }
}

val repositoryModule = module {
    single {
        QuoteRepository( get(), get() )
    }
}

