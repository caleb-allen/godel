package main

interface GodelDomain<in T> {
    /**
     * retrieve the encode of element [element] in the set [T]
     * e.g. A = 1, B = 2, C = 3, etc.
     *
     * Index must be perfect and repeatable
     */
    fun encode(element: T): Int

//    abstract fun measure(elem)

    fun name(): String

}
