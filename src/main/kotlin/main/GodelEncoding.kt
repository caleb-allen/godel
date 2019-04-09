package main

import prime.CommonsPrime

class GodelEncoding<T>(
    private val domain: GodelDomain<T>
){
    companion object {
        private val primes = CommonsPrime()
    }
    fun encode(entity: T): Int {
        // get the index of the entity in its domain
        val index = domain.index(entity)

        // the index of this entity is the nth prime, where n is the index
        // in the domain
        val nthPrime = primes.prime(index)
        println("$entity: fetching nth prime in domain ${domain.name()}. N = $index. Hash is $nthPrime")
        return nthPrime
    }

    fun encode(entities: Iterable<T>): Int =
            entities.map {
                encode(it)
            }.reduce { acc, i -> acc * i }


    fun contains(hash: Int, query: T) =
            encode(query).let {
                println("hash: $hash")
                println("query: $it")
                hash % it == 0
            }
    fun contains(hash: Int, query: Iterable<T>) =
        encode(query).let {
            println("hash: $hash")
            println("query: $it")
            hash % it == 0
        }
}
