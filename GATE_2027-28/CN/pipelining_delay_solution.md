# Packet Switching Delay Calculation

**Step 1: Identify the Given Parameters**
*   **Packet size ($L$):** 1000 bits
*   **Number of packets ($N$):** 1000
*   **Bandwidth ($B$):** $1 \text{ Mbps} = 10^6 \text{ bits/second}$
*   **Distance per link ($d$):** $100 \text{ km} = 10^5 \text{ meters}$
*   **Signal speed ($v$):** $10^8 \text{ meters/second}$
*   **Number of hops ($h$):** 3 (Source $\rightarrow$ $R_1$ $\rightarrow$ $R_2$ $\rightarrow$ Destination)

**Step 2: Calculate Delays for a Single Packet**
*   **Transmission Delay ($T_t$)** is the time to push a packet onto the link:
    $$T_t = \frac{L}{B} = \frac{1000}{10^6} = 10^{-3} \text{ seconds} = \mathbf{1 \text{ ms}}$$
*   **Propagation Delay ($T_p$)** is the time for a bit to travel across one link:
    $$T_p = \frac{d}{v} = \frac{10^5}{10^8} = 10^{-3} \text{ seconds} = \mathbf{1 \text{ ms}}$$

**Step 3: Apply the Pipelining Formula**
In a store-and-forward packet-switched network, breaking a file into packets allows for pipelining, meaning routers can process and transmit different packets simultaneously across different links. The total time is the delay for the very first packet to clear the entire network, plus the time it takes for the remaining packets to arrive behind it.

1.  **First packet delay:** The first packet must cross all 3 links sequentially, incurring both transmission and propagation delays at each hop.
    $$\text{First Packet Time} = h \times (T_t + T_p) = 3 \times (1 \text{ ms} + 1 \text{ ms}) = \mathbf{6 \text{ ms}}$$

2.  **Remaining packets delay:** Because the bottleneck link limits the rate, the remaining 999 packets arrive continuously at the destination, one after another, every $T_t$ (1 ms).
    $$\text{Remaining Packets Time} = (N - 1) \times T_t = 999 \times 1 \text{ ms} = \mathbf{999 \text{ ms}}$$

3.  **Total Delay:**
    $$\text{Total Time} = 6 \text{ ms} + 999 \text{ ms} = \mathbf{1005 \text{ ms}}$$

**Conclusion**
The total sum of transmission and propagation delays is **1005 ms**, making **Option (A)** the correct answer.