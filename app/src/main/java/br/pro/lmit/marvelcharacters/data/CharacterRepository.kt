package br.pro.lmit.marvelcharacters.data

import br.pro.lmit.marvelcharacters.data.entity.CharacterDataWrapper
import br.pro.lmit.marvelcharacters.data.Result

interface CharacterRepository {

    suspend fun getCharacters() : Result<CharacterDataWrapper>

    suspend fun getCharacter(id: Int) : Result<CharacterDataWrapper>
}