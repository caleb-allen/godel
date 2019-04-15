package main

interface GodelDomain<in T> {
    /**
     * retrieve the encode of element [element] in the set [T]
     * e.g. A = 1, B = 2, C = 3, etc.
     *
     * Index must be perfect and repeatable
     */
    abstract fun encode(element: T): Int

//    abstract fun measure(elem)

    abstract fun name(): String

}

/**
 * T is parent domain type
 */
abstract class GodelSubDomain{

}