import main.*
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CharEncodingTest {
    @Test
    fun charIndexTest(){
        val e = UtfCharDomain()
        assertEquals(65, e.encode('A'))
        assertEquals(101, e.encode('e'))
        assertEquals(33, e.encode('!'))
    }


    @Test
    fun charEncodingTest(){
        val charEncoding = GodelEncoding(SimpleCharDomain())

        val hello = "hello"
        hello.forEach {
            println("$it: ${charEncoding.encode(it)}")
        }
    }



    @Test
    fun wordEncodingTest(){
        val wordEncoding = GodelEncoding(SimpleCharDomain())

        val word = "hello"

        val hash = wordEncoding.encode(word.asIterable())
        println(hash)
    }

    @Test
    fun wordContainsCharTest(){
        val wordEncoding = GodelEncoding(SimpleCharDomain())

        val word = "hi"

        val hash = wordEncoding.encode(word.asIterable())

        assertTrue {
            wordEncoding.contains(hash, 'i')
        }

        assertFalse {
            wordEncoding.contains(hash, 'l')
        }


    }


    @Test
    fun reduceCharactersAsWordTest(){
        val wordEncoding = GodelEncoding(SimpleCharDomain())

//        val wordChars = "word".toCharArray()
        val wordChars = CharArray(5)
        wordChars[0] = 'l'
        wordChars[1] = 'a'
        wordChars[2] = 'r'
        wordChars[3] = 'g'
        wordChars[4] = 'e'

        val charsHashes = wordChars.map {
            wordEncoding.encode(it)
        }

        var totalHash = 1L
        charsHashes.forEach {
            println("Hash: $totalHash -- New: $it")
            totalHash = Math.multiplyExact(totalHash, it.toLong())
        }

        val hash: Long = wordEncoding.encode("large".asIterable())

        assertEquals(hash, totalHash)

        assertEquals(wordEncoding.encode('h'), wordEncoding.encode("h".asIterable()))


        // hash of 'lar'
        var subHash = 1L
        charsHashes.subList(0, 3).map {
//            subHash *= it
            subHash = Math.multiplyExact(subHash, it)
        }
        // hash of 'lar'
        var endHash = 1L
        charsHashes.subList(3, 5).map {
//            endHash *= it
            endHash = Math.multiplyExact(endHash, it)
        }

        println("total: $totalHash")
        println("sub $subHash end $endHash")
        println("sub * end: ${Math.multiplyExact(subHash, endHash)}")
//        assertEquals(totalHash, subHash * endHash)
        assertEquals(totalHash, Math.multiplyExact(subHash, endHash))
        assertEquals(endHash, totalHash / subHash)
//        assertEquals(
//            BigInteger.valueOf(totalHash.toLong()),
//            BigInteger.valueOf(subHash.toLong())
//                .times(BigInteger.valueOf(endHash.toLong()))
//        )
        assertEquals(subHash, totalHash / endHash)
        assertTrue {
            totalHash.rem(subHash) == 0L
        }

    }
    @Test
    fun lessSimpleWordContainsCharTest(){
        val wordEncoding = GodelEncoding(SimpleCharDomain())

        val word = "large"

        val hash = wordEncoding.encode(word.asIterable())


        assertFalse {
            wordEncoding.contains(hash, 'i')
        }

        assertTrue {
            wordEncoding.contains(hash, 'a')
        }
    }
    @Test
    fun simpleChars(){
        val ch = SimpleCharDomain()
    }
    @Test
    fun lessSimpleWordContainsWordTest(){
        val wordEncoding = WordEncoding()

        val word = "large"

        val hash = wordEncoding.encode(word)

        println("hash:")
        println(hash)
        assertTrue { wordEncoding.contains(hash, "la") }
        assertTrue { wordEncoding.contains(hash, "large") }
        assertTrue { wordEncoding.contains(hash, "arge") }
        assertFalse { wordEncoding.contains(hash, "al") }
        assertFalse { wordEncoding.contains(hash, "ral") }
        assertFalse { wordEncoding.contains(hash, "egral") }
        assertFalse { wordEncoding.contains(hash, "egra") }
        // order is not being preserved as it should
        // e^1 * r^2 * g^3 is a factor of l^1 * a^2 * r^3 * g^4 * e^5

        // specifically
        // (r^3 * g^4 * e^5) % (e^1 * r^2 * g^3) == 0
        // is this an intended consequence?
        // really any exponent would still be.
        // Why is the *measure* function only in the power?

        // Perhaps this measure function is what must be identical (and previously known?)
        // in order to determine equality.

        // unless there exists an alternative method of detecting the relation between
        // sets (in this case a word), where the only commonality is the base primes (characters)
        assertFalse { wordEncoding.contains(hash, "erg") }
        assertFalse { wordEncoding.contains(hash, "egr") }
        assertFalse { wordEncoding.contains(hash, "gre") }
        assertFalse { wordEncoding.contains(hash, "le") }
    }
}