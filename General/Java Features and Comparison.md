# Java Features and Comparison

## Java 11

- In this version, **JRE or Server JRE is no longer offered**. Only JDK is offered. Auto-update has been removed from JRE installations.
- **Type Inference** for Lambda Parameters
- Epsilon Garbage Collector
  - The No-Op garbage collector is ideal for handling only memory allocation without implementing any memory reclamation apparatus. Epsilon GC is also helpful for **cost-benefit comparison of other garbage collectors and performance testing**.
- **Z Garbage Collector**
  - It is a **scalable low-latency garbage collector**. The Z garbage collector ensures that <u>pause times do not go beyond 10ms</u>. It also ensures that pause times do not increase with the size of the heap or live-set. Finally, ZGC also manages heaps of varying sizes from 100 megabytes to multi terabytes.
- Dynamic Allocation of Compiler Threads
  Dynamic control of compiler threads is possible now in Java 11 with a new command line flag. The VM starts numerous compiler threads on systems with multiple CPUs in the tiered compilation mode. There is no concern for the number of compilation requests or available memory with this command line flag. However, this can lead to inefficient use of resources as idle threads could also consume memory. So, with the new command line flag, implementation has changed. Now, only one compiler thread of each type initiates at startup, and then the starting and shutdown of subsequent threats are subject to dynamic management.