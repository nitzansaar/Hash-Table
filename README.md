# Hash Table Implementations

This project implements different types of hash tables using various collision resolution strategies. It includes three implementations: closed hashing with double hashing, closed hashing with linear probing, and open hashing with separate chaining. These implementations provide efficient ways to store and retrieve key-value pairs with collision handling mechanisms.

## Table of Contents

- [Hash Table Implementations](#hash-table-implementations)
    - [Introduction](#introduction)
    - [Implementations](#implementations)
    - [Usage](#usage)
    - [What I Learned](#what-i-learned)

## Introduction

Hash tables are fundamental data structures used to store key-value pairs for efficient data retrieval. This project explores different techniques for implementing hash tables, specifically focusing on collision resolution strategies. The goal is to provide insight into how different collision resolution methods affect the performance and behavior of hash tables.

## Implementations

The project includes the following hash table implementations:

1. **HashTableClosedHashingDH**: Implements closed hashing using double hashing to handle collisions.
2. **HashTableClosedHashingLP**: Implements closed hashing using linear probing for collision resolution.
3. **HashTableOpenHashing**: Implements open hashing with separate chaining to resolve collisions.

Each implementation is designed to provide efficient and reliable key-value storage with its respective collision resolution approach.

## Usage

To use the hash table implementations, follow these steps:

1. Include the relevant implementation classes in your project.
2. Create instances of the hash table classes based on your chosen collision resolution strategy.
3. Use the `put`, `get`, `remove`, and `containsKey` methods to manage key-value pairs.

## What I Learned

Working on this project, I gained valuable insights into several key areas:

- **Hashing Techniques**: I learned about different techniques for hashing strings, including polynomial hashing, which is essential for mapping keys to indices in hash tables.
- **Collision Resolution**: I explored various collision resolution strategies, such as double hashing, linear probing, and separate chaining. Understanding how each strategy affects performance and memory usage was enlightening.
- **Load Factor and Rehashing**: I learned the significance of load factor in hash table performance and how rehashing is used to maintain an optimal load factor.
- **Data Structure Design**: Implementing the LinkedList class for separate chaining provided hands-on experience with designing and working with custom data structures.
