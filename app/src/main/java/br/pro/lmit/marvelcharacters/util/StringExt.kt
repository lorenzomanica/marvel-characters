package br.pro.lmit.marvelcharacters.util

import java.math.BigInteger
import java.security.MessageDigest

operator fun String.times(i: Int) = (1..i).fold("") {
    acc: String, _: Int -> acc + this
}

fun String.generateMD5Sum() : String {
    val digest = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    val sum = BigInteger(1, digest).toString(16)
    return "0" * (32 - sum.length) + (sum)
}