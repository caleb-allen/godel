package main

import prime.CommonsPrime

class GodelEncoding<T>(
    private val domain: GodelDomain<T>
){
    companion object {
        val primes = CommonsPrime()
    }
    fun encode(entity: T): Long {
        // get the index of the entity in its domain
        val index = domain.encode(entity)

        // the index of this entity is the nth prime, where n is the index
        // in the domain
        val nthPrime = primes.prime(index)
        println("$entity: fetching nth prime in domain ${domain.name()}. N = $index. Hash is $nthPrime")
        // the "base" primes are okay being calculated as an integer, at this point.
        // and combination of integers should be encoded as a long.
        return nthPrime.toLong()
    }

    fun encode(entities: Iterable<T>): Long =
            entities.map {
                encode(it)
            }.reduce { acc, i ->
                Math.multiplyExact(acc, i)
            }


    fun contains(hash: Long, query: T) =
            encode(query).let {
                println("hash: $hash")
                println("query: $it")
                hash % it == 0L
            }
    fun contains(hash: Long, query: Iterable<T>) =
        encode(query).let {
            println("hash: $hash")
            println("query: $it")
            hash % it == 0L
        }
}
