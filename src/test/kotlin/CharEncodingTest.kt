import main.CharDomain
import main.GodelEncoding
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CharEncodingTest {
    @Test
    fun charIndexTest(){
        val e = CharDomain()
        assertEquals(65, e.index('A'))
        assertEquals(101, e.index('e'))
        assertEquals(33, e.index('!'))
    }


    @Test
    fun charEncodingTest(){
        val charEncoding = GodelEncoding(CharDomain())

        val sample = charEncoding.encode('A')
        println(sample)
        val hello = "hello"
        hello.forEach {
            println("$it: ${charEncoding.encode(it)}")
        }
    }



    @Test
    fun wordEncodingTest(){
        val wordEncoding = GodelEncoding(CharDomain())

        val word = "hello"

        val hash = wordEncoding.encode(word.asIterable())
        println(hash)
    }

    @Test
    fun wordContainsCharTest(){
        val wordEncoding = GodelEncoding(CharDomain())

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
        val wordEncoding = GodelEncoding(CharDomain())

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
        val wordEncoding = GodelEncoding(CharDomain())

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
    fun lessSimpleWordContainsWordTest(){
        val wordEncoding = GodelEncoding(CharDomain())

        val word = "large"

        val hash = wordEncoding.encode(word.asIterable())

        assertTrue {
            wordEncoding.contains(hash, "la".asIterable())
        }
    }
}