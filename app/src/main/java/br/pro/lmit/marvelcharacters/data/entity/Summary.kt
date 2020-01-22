package br.pro.lmit.marvelcharacters.data.entity

sealed class Summary(
    val resourceURI: String?,
    val name: String?
)

class ComicSummary(resourceURI: String?, name: String?) : Summary(resourceURI, name)

class EventSummary(resourceURI: String?, name: String?) : Summary(resourceURI, name)

class SeriesSummary(resourceURI: String?, name: String?) : Summary(resourceURI, name)

class StorySummary(resourceURI: String?, name: String?,
    val type: String?
) : Summary(resourceURI, name)
