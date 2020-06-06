

# RMQ API
An API which resolves the RMQ problem in O(1) with O(n*log(n)) preprocessing.
## What is RMQ
RMQ stands for Range Minimum Query. The problem consists in finding the smallest element in a contiguous segment from an array of numbers as efficiently as possible.
For example, consider the 1-indexed array
```
{20, 50, 229, 4871, 59, 5, 18, 221, 85}
```
rmq(1, 1) = 20,  rmq(1, 6) = 5, rmq(3, 4) = 229

## API Features
- Computes rmq(a, b) in O(1). It does not matter how long the searched segement is. The algorithm always does ~10 operations to find the minimum number.
- Each user can have a set of such problem instances (arrays)
- The API is secured, name and password are required to do any operation
- The algorithm uses Sparse Table data structure ([https://cp-algorithms.com/data_structures/sparse-table.html](https://cp-algorithms.com/data_structures/sparse-table.html)). The core techniques involved are dynamic programming and result-oriented binary search.

## Documentation