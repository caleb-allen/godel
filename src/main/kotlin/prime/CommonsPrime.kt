package prime

import main.PrimeMap
import org.apache.commons.math3.primes.Primes

class CommonsPrime: PrimeMap {
    private val calculatedPrimes: MutableList<Int> = mutableListOf(2)
    override fun prime(i: Int): Int {
        while (i >= calculatedPrimes.size) {
            val highest = calculatedPrimes.last()
            val next = Primes.nextPrime(highest + 1)
            calculatedPrimes.add(next)
        }
        return calculatedPrimes[i]
    }
}