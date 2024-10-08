# Benchmark Results

## Counter: 500_000_000

| Heap Size | State             | Time (ms) | Time (s) |
|-----------|-------------------|-----------|----------|
| 128m      | Original          | 13791     | 13       |
| 256m      | Original          | 13355     | 13       |
| 512m      | Original          | 12985     | 12       |
| 1024m     | Original          | 13352     | 13       |
| 2048m     | Original          | 14453     | 14       |
| 4096m     | Original          | 37604     | 37       |
| 8192m     | Original          | 36960     | 36       |
| 128m      | 1. Integer to int | 4349      | 4        |
| 256m      | 1. Integer to int | 3817      | 3        |
| 512m      | 1. Integer to int | 4285      | 4        |
| 2048m     | 1. Integer to int | 4452      | 4        |
| 8192m     | 1. Integer to int | 7747      | 7        |

## Counter: 2_000_000_000

| Heap Size | State                       | Time (ms) | Time (s) |
|-----------|-----------------------------|-----------|----------|
| -         | -                           | -         | -        |
| 128m      | Original                    | 54918     | 54       |
| 512m      | Original                    | 52705     | 52       |
| 2048m     | Original                    | 53524     | 53       |
| 8192m     | Original                    | 51864     | 51       |
| 64m       | 1. Summator: Integer to int | 18010     | 18       |
| 128m      | 1. Summator: Integer to int | 17639     | 17       |
| 512m      | 1. Summator: Integer to int | 14924     | 14       |
| 8192m     | 1. Summator: Integer to int | 17521     | 17       |
| 64m       | 2. Data: Integer to int     | 12699     | 12       |
| 128m      | 2. Data: Integer to int     | 12430     | 12       |
| 512m      | 2. Data: Integer to int     | 12326     | 12       |
| 8192m     | 2. Data: Integer to int     | 19992     | 19       |
| 64m       | 3. List to int              | 7568      | 7        |
| 128m      | 3. List to int              | 7498      | 7        |
| 512m      | 3. List to int              | 7463      | 7        |
| 8192m     | 3. List to int              | 7522      | 7        |

## Changes

### 1. Summator: Integer to int

Replace Integer with int

### 2. Data: Integer to int

Replace Integer with int

### 3. List to int

Replace List 'listValues' with int 'counter'

## Summary

- The usage of optimized data types (int instead of Integer) has a significant impact on the performance of the garbage
  collector. I.e. the Garbage Collector have not been called at all in the case of the 2_000_000_000 counter.

```text
[gc] Using G1
[gc] ConcGCThreads: 3 offset 22
[gc] ParallelGCThreads: 10
[gc] Initialize mark stack with 4096 chunks, maximum 524288

```