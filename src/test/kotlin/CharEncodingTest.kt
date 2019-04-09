import main.CharDomain
import main.GodelEncoding
import org.junit.Test
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

        var totalHash: Int = 1
        val hashChars = charsHashes.forEach {
            totalHash *= it
        }

        val hash: Int = wordEncoding.encode("large".asIterable())

        assertEquals(hash, totalHash)

        assertEquals(wordEncoding.encode('h'), wordEncoding.encode("h".asIterable()))

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