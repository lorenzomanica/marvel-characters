package br.pro.lmit.marvelcharacters.injection.module

import br.pro.lmit.marvelcharacters.BASE_URL
import br.pro.lmit.marvelcharacters.data.api.CharacterApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


@Module
object ApiModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideCharacterApi(retrofit: Retrofit) : CharacterApi {
        return retrofit.create(CharacterApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

}