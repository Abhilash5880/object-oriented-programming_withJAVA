# Distance Vector Routing Update Solution

**Step 1: Determine the Immediate Vector Updates**

When the cost of link N2-N3 reduces from 6 to 2, the problem states that the incident nodes (N2 and N3) immediately change *only* that specific entry in their distance vectors before the next full round of updates.

*   **Old $DV_{N2}$:** $(1, 0, 6, 7, 3)$
*   **Intermediate $DV_{N2}$:** $(1, 0, 2, 7, 3)$ *(Distance to N3 is updated to 2)*
*   **Old $DV_{N3}$:** $(7, 6, 0, 2, 6)$
*   **Intermediate $DV_{N3}$:** $(7, 2, 0, 2, 6)$ *(Distance to N2 is updated to 2)*

Node N4 is not incident to the changed link, so its distance vector remains unchanged:

*   **Current $DV_{N4}$:** $(8, 7, 2, 0, 4)$

**Step 2: The Next Round of Updates at Node N3**

During the next update round, N3 receives the latest distance vectors from its direct neighbors, N2 and N4. 
N3 will calculate its new shortest paths using the Bellman-Ford equation: 

$$
D_{N3}(x) = \min \{ c(N3, N2) + DV_{N2}(x),\; c(N3, N4) + DV_{N4}(x) \}
$$

The link costs from N3 to its neighbors are now $c(N3, N2) = 2$ and $c(N3, N4) = 2$.

**Step 3: Calculating the New Distances for N3**

*   **Distance to N1:** $\min(2 + 1,\; 2 + 8) = \min(3, 10) = \mathbf{3}$
*   **Distance to N2:** The direct cost is $\mathbf{2}$.
*   **Distance to N3:** The distance to itself is always $\mathbf{0}$.
*   **Distance to N4:** The direct cost is $\mathbf{2}$.
*   **Distance to N5:** $\min(2 + 3,\; 2 + 4) = \min(5, 6) = \mathbf{5}$

**Conclusion**

Combining these calculated shortest paths, the new distance vector at node N3 is **(3, 2, 0, 2, 5)**. Therefore, **Option (A)** is the correct answer.