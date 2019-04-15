package main

import java.math.BigInteger

class WordEncoding {
    private val charEncoding = GodelEncoding(SimpleCharDomain())
    private val charDomain = SimpleCharDomain()
    fun encode(word: String): BigInteger =
        word.asIterable()
////            charEncoding.encode(it)
////        }.map {
////            BigInteger.valueOf(it)!!
        .mapIndexed { index, char ->
            val godelIndex = GodelEncoding.primes.prime(index).toBigInteger()

            val godelPower = charDomain.encode(char)
            godelIndex.pow(godelPower)!!
//            val godelIndex = encode + 1
//            println("godel encode: $godelIndex")
//            charHash.pow(godelIndex)!!
        }.reduce { acc, hashVal ->
            acc.multiply(hashVal)
        }

    fun contains(hash: BigInteger, word: String): Boolean{
        val queryHash = encode(word)
        println("Hash: $hash")
        println("QueryHash $word: $queryHash")
//        return hash.remainder(queryHash) == BigInteger.ZERO
//        queryHash.modPow()
        return hash.mod(queryHash) == BigInteger.ZERO
//        return queryHash.mod(hash) == BigInteger.ZERO
    }
}