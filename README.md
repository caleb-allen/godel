# Godel

This repository is a proof of concept of an implementation of [Godel Hashes](https://doi.org/10.1109/SCAM.2014.40),
a perfect and compact hash which preserves structural information.


Here, a simple encoding of alphabetical characters is used as the prime basis for a countable total order.

E.g.
`'a' = 1, 'b' = 2 ... 'z' = 26`


For encoding purposes, a "word", or sequence of characters, is defined as a function of the character domain
and a range of integers which indicate an index in the word.

E.g.
```
f = "hello"

f(0) = 'h'
f(1) = 'e'
f(2) = 'l'
f(3) = 'l'
f(4) = 'o'
```

Using this functional interpretation of a word, we can use Definition V.3 of the paper to compute an order-preserving
Godel Hash of any word.

As demonstrated in the linked paper, Godel Hashes are subject to all set operations. This allows efficient search of arbitrary data using the hash itself, **not** the data.
