import main.CharEncoding
import org.junit.Test
import kotlin.test.assertEquals

class CharEncodingTest {
    @Test
    fun charIndexTest(){
        val e = CharEncoding()
        assertEquals(65, e.index('A'))
        assertEquals(101, e.index('e'))
        assertEquals(33, e.index('!'))
    }
}