package br.pro.lmit.marvelcharacters.data.entity

data class CharacterDataWrapper(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionURL: String?,
    val data: CharacterDataContainer?,
    val etag: String?
)