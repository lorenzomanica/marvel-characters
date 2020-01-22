package br.pro.lmit.marvelcharacters.data.entity

import java.util.*

/*Character {
    id (int, optional): The unique ID of the character resource.,
    name (string, optional): The name of the character.,
    description (string, optional): A short bio or description of the character.,
    modified (Date, optional): The date the resource was most recently modified.,
    resourceURI (string, optional): The canonical URL identifier for this resource.,
    urls (Array[Url], optional): A set of public web site URLs for the resource.,
    thumbnail (Image, optional): The representative image for this character.,
    comics (ComicList, optional): A resource list containing comics which feature this character.,
    stories (StoryList, optional): A resource list of stories in which this character appears.,
    events (EventList, optional): A resource list of events in which this character appears.,
    series (SeriesList, optional): A resource list of series in which this character appears.
}*/
data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: Date?,
    val resourceURI: String?,
    val urls: List<Url>?,
    val thumbnail: Image?,
    val comics: SummaryList<ComicSummary>?,
    val stories: SummaryList<StorySummary>?,
    val events: SummaryList<EventSummary>?,
    val series: SummaryList<SeriesSummary>?
)