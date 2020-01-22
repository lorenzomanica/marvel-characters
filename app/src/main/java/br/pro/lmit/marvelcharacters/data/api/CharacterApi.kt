package br.pro.lmit.marvelcharacters.data.api

import br.pro.lmit.marvelcharacters.data.entity.CharacterDataWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("ts") ts: Long,
        @Query("apikey") key: String,
        @Query("hash") hash: String
    ): Response<CharacterDataWrapper>

    @GET("/v1/public/characters/{id}")
    suspend fun getCharacters(
        @Path("id") id: Int,
        @Query("ts") ts: Long,
        @Query("apikey") key: String,
        @Query("hash") hash: String
    ): Response<CharacterDataWrapper>
}