package main

/**
 * Godel index of the character set, representing the set 0-65535 of the Java Unicode character set
 *
 */
class CharDomain: GodelDomain<Char>() {
    override fun name(): String = "Char"

    override fun index(element: Char): Int = element.toInt()
}