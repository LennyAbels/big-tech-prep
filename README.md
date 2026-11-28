# Big Tech Prep

Repositorio de preparación técnica: algoritmos en Java, desarrollo backend, clientes en Python y despliegue en la nube.

## Progreso de Algoritmos (LeetCode)

| # | Problema | Tema | Tiempo | Espacio | Solución |
|---|----------|------|--------|---------|----------|
| 1 | Two Sum | Arrays & Hashing | O(n²) | O(1) | [Java](./leetcode/arrays-hashing/TwoSum.java) |
| 217 | Contains Duplicate | Arrays & Hashing | O(n) | O(n) | [Java](./leetcode/arrays-hashing/ContainsDuplicate.java) |
| 242 | Valid Anagram | Arrays & Hashing | O(n) | O(1) | [Java](./leetcode/arrays-hashing/ValidAnagram.java) |
| 125 | Valid Palindrome | Two Pointers | O(n) | O(1) | [Java](./leetcode/two-pointers/ValidPalindrome.java) |
| 121 | Best Time to Buy and Sell Stock | Two Pointers | O(n) | O(1) | [Java](./leetcode/two-pointers/BestTimeToBuyAndSellStock.java) |
| 49 | Group Anagrams | Arrays & Hashing | O(n · k log k) | O(n · k) | [Java](./leetcode/arrays-hashing/GroupAnagrams.java) |
| 347 | Top K Frequent Elements | Arrays & Hashing | O(n) | O(n) | [Java](./leetcode/arrays-hashing/TopKFrequentElements.java) |
| 238 | Product of Array Except Self | Arrays & Hashing | O(n) | O(1) | [Java](./leetcode/arrays-hashing/ProductOfArrayExceptSelf.java) |
| 128 | Longest Consecutive Sequence | Arrays & Hashing | O(n) | O(n) | [Java](./leetcode/arrays-hashing/LongestConsecutiveSequence.java) |

## Desarrollo Backend & Cloud

- **[backend-api](./backend-api)**: API REST en Java 25, Spring Boot y Spring Data JPA conectada a base de datos SQL (H2), dockerizada (Eclipse Temurin 25) y desplegada en Render (`https://big-tech-prep.onrender.com/api/tasks`).
- **[python-client](./python-client/api_client.py)**: Cliente HTTP en Python (`requests`) para consumir los endpoints CRUD con manejo de excepciones y timeouts.
