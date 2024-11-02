package app.jsapps.retroficpractice.di

import app.jsapps.retroficpractice.data.SpotifyApiService
import app.jsapps.retroficpractice.data.TokenApiService
import app.jsapps.retroficpractice.repository.NetworkRepository
import app.jsapps.retroficpractice.repository.RepositoryImpl
import app.jsapps.retroficpractice.repository.TokenRepository
import app.jsapps.retroficpractice.repository.TokenRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.internal.tls.OkHostnameVerifier
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named("Api")
    fun provideRetrofit(client: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.spotify.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    @Provides
    @Singleton
    @Named("Accounts")
    fun provideRetrofitAcc(client: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://accounts.spotify.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideInterceptor():OkHttpClient{
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideSpotifyApiService(@Named("Api")retrofit: Retrofit):SpotifyApiService{
        return retrofit.create(SpotifyApiService::class.java)
    }

    @Provides
    fun provideAccountService(@Named("Accounts")retrofit: Retrofit):TokenApiService{
        return retrofit.create(TokenApiService::class.java)
    }

    @Provides
    fun provideApiRepository(spotifyApiService: SpotifyApiService):NetworkRepository{
        return RepositoryImpl(
            spotifyApiService
        )
    }

    @Provides
    fun provideTokenRepository(tokenApiService: TokenApiService): TokenRepository {
        return TokenRepositoryImpl(
            tokenApiService
        )
    }
}