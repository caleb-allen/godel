package main

import java.util.*

/**
 * Godel encode of the character set, representing the set 0-65535 of the Java Unicode character set
 *
 */
interface CharDomain: GodelDomain<Char>

class SimpleCharDomain: CharDomain{

    private val a = 'a'
    private val chars = (0 until 26).map {
        a.plus(it) to it
    }.toMap()


    override fun encode(element: Char): Int = chars.getValue(element)

    override fun name(): String = "SimpleCharDomain"

}

class UtfCharDomain: CharDomain{
    override fun encode(element: Char): Int = element.toInt()

    override fun name(): String = "UtfCharDomain"

}