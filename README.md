# Async Http Client

This exercise provides three different implementations of asynchronous HTTP GET client calls with specified URL, total number of calls, and maximum number of simultaneous calls.  All three implementations use ExecutorService framework and newFixedThreadPool method to set maximum number of simultaneous HTTP GET calls.

# Implementation I

In HttpClient class, method client1 is the first implementation with use of awaitTermiantion method and nested for loop to place print statements after completions of each batch of maximum number of simultaneous calls.

# Implementation II

In HttpClient class, method client2 is the second implementation with use of awaitTermination method to place print statements after completion of all calls.

# Implementation III

In HttpClient class, method client3 is the third implementation with use of Future get method and submit method, to place print statements after completion of every individual call.

# Running the application

From IDE, these three implementations can be run from HttpClientTest class, running methods client1Test, client2Test, and client3Test individually or all together sequentially.
