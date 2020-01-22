package br.pro.lmit.marvelcharacters.data.entity


/*
Image {
path (string, optional): The directory path of to the image.,
extension (string, optional): The file extension for the image.
}
* */

data class Image(
    val path: String?,
    val extension: String?
)