package main

/**
 * Godel encoding of the character set, representing the set 0-65535 of the Java Unicode character set
 *
 */
class CharEncoding: GodelEncoding<Char>() {
    override fun index(element: Char): Int = element.toInt()
}