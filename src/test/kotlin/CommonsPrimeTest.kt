import org.jetbrains.annotations.TestOnly
import org.junit.Test
import prime.CommonsPrime

class CommonsPrimeTest {
    @Test
    fun testPrime() {
        val p = CommonsPrime()

        assert(p.prime(0) == 2)
        assert(p.prime(1) == 3)
        assert(p.prime(5) == 13)
    }
}