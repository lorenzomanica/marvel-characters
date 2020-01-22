package br.pro.lmit.marvelcharacters.data

import br.pro.lmit.marvelcharacters.MARVEL_PRIVATE_KEY
import br.pro.lmit.marvelcharacters.MARVEL_PUBLIC_KEY
import br.pro.lmit.marvelcharacters.data.api.CharacterApi
import br.pro.lmit.marvelcharacters.data.entity.CharacterDataWrapper
import br.pro.lmit.marvelcharacters.injection.component.DaggerRepositoryInjector
import br.pro.lmit.marvelcharacters.injection.component.RepositoryInjector
import br.pro.lmit.marvelcharacters.injection.module.ApiModule
import br.pro.lmit.marvelcharacters.util.generateMD5Sum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class DefaultCharacterRepository : CharacterRepository {


    @Inject
    lateinit var api: CharacterApi

    private val injector: RepositoryInjector = DaggerRepositoryInjector
        .builder()
        .apiModule(ApiModule)
        .build()

    init {
        injector.inject(this)
    }


    override suspend fun getCharacters(): Result<CharacterDataWrapper> =
        withContext(Dispatchers.IO) {
            val ts = System.currentTimeMillis()
            val content = "${ts}${MARVEL_PRIVATE_KEY}${MARVEL_PUBLIC_KEY}"
            val hash = content.generateMD5Sum()

            try {
                val response = api.getCharacters(ts, MARVEL_PUBLIC_KEY, hash)
                if (response.isSuccessful) {
                    response.body()?.let {
                        return@withContext Result.Success(it)
                    }
                } else {
                    return@withContext Result.Error(IllegalStateException(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }

            return@withContext Result.Error(IllegalStateException())

        }

    override suspend fun getCharacter(id: Int): Result<CharacterDataWrapper> =
        withContext(Dispatchers.IO) {
            val ts = System.currentTimeMillis()
            val content = "${ts}${MARVEL_PRIVATE_KEY}${MARVEL_PUBLIC_KEY}"
            val hash = content.generateMD5Sum()

            try {
                val response = api.getCharacters(id, ts, MARVEL_PUBLIC_KEY, hash)
                if (response.isSuccessful) {
                    response.body()?.let {
                        return@withContext Result.Success(it)
                    }
                } else {
                    return@withContext Result.Error(IllegalStateException(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }

            return@withContext Result.Error(IllegalStateException())

        }
}