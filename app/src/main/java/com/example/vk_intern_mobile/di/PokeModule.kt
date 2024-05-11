package com.example.vk_intern_mobile.di

import com.example.vk_intern_mobile.data.PokemonApi
import com.example.vk_intern_mobile.data.PokemonRepositoryImpl
import com.example.vk_intern_mobile.domain.repositories.PokemonRepository
import com.example.vk_intern_mobile.domain.usecases.GetAllPokemonsUseCase
import com.example.vk_intern_mobile.domain.usecases.GetSinglePokemonUseCase
import com.example.vk_intern_mobile.domain.usecases.PokemonUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokeModule {
    @Provides
    @Singleton
    fun provideProductsApi() : PokemonApi {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApi) : PokemonRepository {
        return PokemonRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providePokemonUseCases(repository: PokemonRepository) :PokemonUseCases {
        return PokemonUseCases(
            getAllPokemonsUseCase = GetAllPokemonsUseCase(repository),
            getSinglePokemonUseCase = GetSinglePokemonUseCase(repository)
        )
    }
}