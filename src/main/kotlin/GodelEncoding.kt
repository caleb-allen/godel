abstract class GodelEncoding<T> {
//    /**
//     * retrieve the prime number of set [T] which perfectly represents [element]
//     */
//    abstract fun encode(element: T): Long

    /**
     * retrieve the index of element [element] in the set [T]
     * e.g. A = 1, B = 2, C = 3, etc.
     *
     * Index must be perfect and repeatable
     */
    abstract fun index(element: T): Long
}