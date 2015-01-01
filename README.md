jppf-tests
===============

This repo contains tests involving [JPPF](http://http://www.jppf.org/).

Installation prerequisites
-------

To run these tests, you need:
- JDK 7 or greater
- Git
- Maven
- JPPF Distribution 4.2.4 or greater

Download the project
-------

git clone https://github.com/jsebrien/jppf-tests.git

JPPFExecutorService - Run tests
-------

mvn clean package exec:java

JPPFExecutorService - Use cases
-------

This test demonstrate how to use a JPPFExecutorService (executing runnables/callables on a JPPF Grid), created from a JPPFTask.
Here we need to make sure that JPPFExecutorService submitted jobs (1 job per runnable/callable) are not enqueued in the same node that had initialized the JPPFExecutorService (otherwise there is a deadlock).
Each callable is generating the prime numbers between 1 and a random limit.

Here is an example output for each generation:

PrimeNumberGenerator(0) - Prime numbers between 1 and 30: 1 2 3 5 7 11 13 17 19 23 29
PrimeNumberGenerator(1) - Prime numbers between 1 and 65: 1 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61
PrimeNumberGenerator(2) - Prime numbers between 1 and 57: 1 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53
PrimeNumberGenerator(3) - Prime numbers between 1 and 59: 1 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53
PrimeNumberGenerator(4) - Prime numbers between 1 and 31: 1 2 3 5 7 11 13 17 19 23 29
PrimeNumberGenerator(5) - Prime numbers between 1 and 27: 1 2 3 5 7 11 13 17 19 23
PrimeNumberGenerator(6) - Prime numbers between 1 and 5: 1 2 3
PrimeNumberGenerator(7) - Prime numbers between 1 and 52: 1 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47
PrimeNumberGenerator(8) - Prime numbers between 1 and 6: 1 2 3 5
PrimeNumberGenerator(9) - Prime numbers between 1 and 17: 1 2 3 5 7 11 13
