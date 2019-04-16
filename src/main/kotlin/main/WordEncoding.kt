package main

import java.math.BigInteger

class WordEncoding {
    private val charDomain = SimpleCharDomain()
    fun encode(word: String): BigInteger =
        word.asIterable()
        .mapIndexed { index, char ->
            val godelIndex = GodelEncoding.primes.prime(index).toBigInteger()

            val godelPower = charDomain.encode(char)
            godelIndex.pow(godelPower)!!
        }.reduce { acc, hashVal ->
            acc.multiply(hashVal)
        }

    fun contains(hash: BigInteger, word: String): Boolean{
        return hash.mod(encode(word)) == BigInteger.ZERO
    }
}