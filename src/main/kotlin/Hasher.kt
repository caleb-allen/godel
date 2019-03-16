interface GodelHash {
    fun divisibleBy(hash: GodelHash): Boolean
}

interface Proposition


/*To construct the Godel encoding of a set, first assume that ¨
every potential element has been assigned a unique prime
number; then compute the product of the primes assigned to
each element; the result is the Godel hash of the set. It is not ¨
necessary to pre-construct the assignment from elements to
primes: new elements may be assigned fresh primes as they
are encountered for the first time*/
/*
Example: If the potential elements of a set are A,
B, C and D, then we can assign these elements the primes
2, 3, 5 and 7 respectively. Thus, the Godel hash of the set ¨
S = {A, C} is the natural number 2 × 5 = 10.
 */

/* each dimension is its own Godel set
GodelString, GodelDocument, GodelTime

Any one instance or entity can have multiple potential encodings

A document's encoding is probably the product of the encodings of the strings within it

The encoding of the string set is the product of the encodings of each character.
Perhaps not including case? the encoding should reflect what one thinks
of. A word in English is the same word whether caps or not, just with
different context.

That context can be its own encoding as well.

The encoding of the character set has elements 0-65535



A string encoding has its own Godel Prime map.

To get the godel hash of a string, first get the godel hash of its character set.

Because a godel hash is perfect, the godel hash of the character set is used as the index for the
string in the String set encoding.

e.g.
"AC" = A, C
charGodelEncoding(A) = 2
charGodelEncoding(C) = 5
2 x 5 = 10

Because a godel hash is perfect, the index of string "AC" in the godel string encoding is 10

stringGodelEncoding(10) = X

and therefore

documentGodelEncoding(X) = Y
One can recursively level the godel structure of information

A godel set, whose members are godel sets of arbitrary encodings


To search a document for a string, you need the string hash of the document's strings

To search a directory for a document, you need the document hash of the directory's documents

To search a directory for a string, you need the string hash of the directory's string


If Alice has a file X and wants to know if Bob has the same file, and has Bob's byte encoding,
Alice takes the byte encoding of X, and checks its inclusion in Bob's byte encoding


Should everything just go down to the byte level? Or should multiple encodings exist?

Permissions could simply be the existence of the hash.


I think yes, multiple encodings should exist, including user-defined encodings.
Like a contact, or a place. Very simple encodings, with a set limit within 10^2


Any blob may have N associated encodings (and subsequent encoded values), where N = 0+


A file probably has a byte encoding
If it is a .kt file, perhaps it has a document encoding. The document encoding
is dependent on the string encoding of its contained strings, let's say SE

The document encoding is the Nth item in the document set, where N = SE.




*/

interface Blob {
    fun contains(blob: Blob): Boolean
    fun equals(blob: Blob): Boolean
}