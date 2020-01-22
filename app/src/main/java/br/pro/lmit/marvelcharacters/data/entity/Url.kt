package br.pro.lmit.marvelcharacters.data.entity

/*
Url {
type (string, optional): A text identifier for the URL.,
url (string, optional): A full URL (including scheme, domain, and path).
}
*/

data class Url(
    val type: String?,
    val url: String?
)