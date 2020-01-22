package br.pro.lmit.marvelcharacters.data.entity


/*
ComicList {
    available (int, optional): The number of total available issues in this list. Will always be greater than or equal to the "returned" value.,
    returned (int, optional): The number of issues returned in this collection (up to 20).,
    collectionURI (string, optional): The path to the full list of issues in this collection.,
    items (Array[ComicSummary], optional): The list of returned issues in this collection.
}

StoryList {
    available (int, optional): The number of total available stories in this list. Will always be greater than or equal to the "returned" value.,
    returned (int, optional): The number of stories returned in this collection (up to 20).,
    collectionURI (string, optional): The path to the full list of stories in this collection.,
    items (Array[StorySummary], optional): The list of returned stories in this collection.
}

EventList {
    available (int, optional): The number of total available events in this list. Will always be greater than or equal to the "returned" value.,
    returned (int, optional): The number of events returned in this collection (up to 20).,
    collectionURI (string, optional): The path to the full list of events in this collection.,
    items (Array[EventSummary], optional): The list of returned events in this collection.
}

SeriesList {
    available (int, optional): The number of total available series in this list. Will always be greater than or equal to the "returned" value.,
    returned (int, optional): The number of series returned in this collection (up to 20).,
    collectionURI (string, optional): The path to the full list of series in this collection.,
    items (Array[SeriesSummary], optional): The list of returned series in this collection.
}
*/

data class SummaryList<T : Summary>(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<T>?
)