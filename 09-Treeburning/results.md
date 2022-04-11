# Results

---

**1. Why did you choose the board size for your finalized table?**

100 is a good size because it is both quite large and quite small at the same time, being big enough to represent a sizable amount of density and being small enough to finish in a reasonable amount of time.

**2. Why did you choose the number of repetitions size for your finalized table?**

The number of repetitions should also have been a multiple of any divisor of 10, since it would avoid creating a repeating decimal that would not fit nicely in the table without rounding.

The program is able to finish around 1.5 seconds.

**3. What did your testing show about changing the board size?**

As the board size increases, the time it takes for the program to finish also drastically increases.

**4. Knowing that there were differences based on boardsize, what was the relationship between the board size and the maximum burn time? How did you test this?**

As board size increases, the maximum burn time also increases because as there are more trees, it is more likely for two trees to be next to each other. This, however, does not scale linearly and does have noticeably diminishing returns.

I have tested this on board sizes of 100, 250, 500, and 1000. The results have been consistent.

Another thing to note is that as the board size increases, the maximum shifts towards when the density is 0.6.

**5. What density of trees yields the maximum burn time?**

For width and height of 100, about 0.62 yielded the highest density.

## Tables

width | height | density | result
--- | --- | --- | ---
100 | 100 | 0.0 | 0.0
100 | 100 | 0.05 | 1.19
100 | 100 | 0.1 | 1.95
100 | 100 | 0.15 | 2.84
100 | 100 | 0.2 | 3.77
100 | 100 | 0.25 | 5.07
100 | 100 | 0.3 | 6.57
100 | 100 | 0.35 | 8.93
100 | 100 | 0.4 | 11.88
100 | 100 | 0.45 | 19.36
100 | 100 | 0.5 | 28.99
100 | 100 | 0.55 | 64.76
100 | 100 | 0.6 | 186.95
100 | 100 | 0.65 | 173.75
100 | 100 | 0.7 | 145.59
100 | 100 | 0.75 | 131.95
100 | 100 | 0.8 | 121.95
100 | 100 | 0.85 | 115.94
100 | 100 | 0.9 | 110.53
100 | 100 | 0.95 | 106.14
100 | 100 | 1.0 | 100.0

width | height | density | result
--- | --- | --- | ---
100 | 100 | 0.55 | 66.48
100 | 100 | 0.56 | 90.91
100 | 100 | 0.57 | 102.95
100 | 100 | 0.58 | 136.17
100 | 100 | 0.59 | 165.51
100 | 100 | 0.6 | 196.18
100 | 100 | 0.61 | 194.32
100 | 100 | 0.62 | 201.21
100 | 100 | 0.63 | 192.42
100 | 100 | 0.64 | 181.37
100 | 100 | 0.65 | 177.69
100 | 100 | 0.66 | 165.35
100 | 100 | 0.67 | 159.57
100 | 100 | 0.68 | 153.77
100 | 100 | 0.69 | 148.63
100 | 100 | 0.7 | 145.45
