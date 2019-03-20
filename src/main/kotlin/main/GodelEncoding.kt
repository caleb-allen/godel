package main

abstract class GodelEncoding<T> {
    /**
     * retrieve the index of element [element] in the set [T]
     * e.g. A = 1, B = 2, C = 3, etc.
     *
     * Index must be perfect and repeatable
     */
    abstract fun index(element: T): Int
}