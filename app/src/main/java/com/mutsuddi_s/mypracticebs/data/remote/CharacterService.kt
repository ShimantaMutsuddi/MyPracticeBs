package com.mutsuddi_s.mypracticebs.data.remote

import com.mutsuddi_s.mypracticebs.data.entitiies.Character
import com.mutsuddi_s.mypracticebs.data.entitiies.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {
    @GET("api/character")
    suspend fun getAllCharacters(): Response<CharacterResponse>
    //suspend fun getAllCharacters(@Query("page") page: Int): Response<CharacterResponse>

    @GET("api/character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<Character>
}