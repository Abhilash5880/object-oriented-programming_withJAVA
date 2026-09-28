# Master Mathematical Formula Sheet

## 1. Data Link Layer (Access Protocols)

*   **CSMA/CD Efficiency:** $\eta = \frac{1}{1+6.44a}$ (where $a = \frac{T_p}{T_d}$).
*   **Pure ALOHA Vulnerable Time:** $2 \times T_t$ (Transmission Time).
*   **Pure ALOHA Throughput:** $S = G \times e^{-2G}$.
    *   *Maximum Throughput:* 18.4% ($0.184$) when $G = \frac{1}{2}$.
*   **Slotted ALOHA Vulnerable Time:** $T_t$.
*   **Slotted ALOHA Throughput:** $S = G \times e^{-G}$.
    *   *Maximum Throughput:* 36.8% ($0.368$) when $G = 1$.
*   **Channel Throughput (General):** $N \times P(1-P)^{N-1}$ gives the probability of success for any station among $N$ total stations.

## 2. TCP Error Control & Timers

*   **Send Window Size:** $\text{Send Window} = \min(\text{rwnd}, \text{cwnd})$.
*   **Retransmission Timer:** $2 \times \text{RTT}$.
*   **Basic Algorithm for Time Out Timer (TOT):**
    *   $TOT_1 = 2 \times IRTT_1$.
    *   $IRTT_{n+1} = \alpha \cdot IRTT_n + (1-\alpha) \cdot ARTT_n$ (where $0 \le \alpha \le 1$, $IRTT$ is Initial RTT, and $ARTT$ is Actual RTT).
*   **Jacobson's Algorithm for TOT:**
    *   **Actual Deviation:** $AD_n = |IRTT_n - ARTT_n|$.
    *   **Next Deviation:** $ID_{n+1} = \alpha \cdot ID_n + (1-\alpha) \cdot AD_n$.
    *   $TOT = 4 \times ID + IRTT$.

## 3. IP Addressing & CIDR Aggregation Rules

To successfully combine/supernet networks into a single CIDR block:
*   **Rule 1:** All networks must be contiguous.
*   **Rule 2:** The total number of IP addresses must be a power of 2, and all block sizes must be the same.
*   **Rule 3:** The very first IP address of the starting block must be perfectly divisible by the total size of the combined supernet block.

---

# Comprehensive Conceptual Notes

## 1. Multiple Access Protocols (ALOHA vs CSMA/CD)
*   **Pure ALOHA:** Any station transmits data at any time. Its primary advantage is simplicity, but because frames can collide with the tail of a previous frame or the head of a future frame, its vulnerable time is $2 \times T_t$.
*   **Slotted ALOHA:** Stations can only transmit at the exact beginning of synchronized time slots. If a station misses the start, it must wait for the next slot. This halves the vulnerable time to $T_t$, doubling the maximum throughput compared to Pure ALOHA.

## 2. TCP Window Management & Congestion Control
*   **Flow Control vs. Congestion Control:** TCP utilizes two distinct windows. The Receiver Window (rwnd) is advertised by the receiver to prevent its buffers from overflowing (Flow Control). The Congestion Window (cwnd) is managed privately by the sender based on network feedback to prevent dropping packets at routers (Congestion Control). The effective Send Window is strictly bounded by the lesser of the two.
*   **Window Operations:**
    *   *Opening:* The right wall moves right, allowing more new bytes into the buffer.
    *   *Closing:* The left wall moves right as bytes are acknowledged by the receiver.
    *   *Shrinking:* The right wall moves left (strictly controlled by the receiver based on network conditions).
*   **AIMD Control Law (Congestion Policy):** TCP adjusts the congestion window using Additive Increase Multiplicative Decrease. The policy cycles through three phases: Slow Start (Exponential Increase starting at 1 MSS), Congestion Avoidance (Additive Increase), and Congestion Detection.

## 3. TCP Timers
*   **Persistence Timer:** Used to resolve a Zero-Window deadlock. If a receiver advertises a window size of 0, the sender pauses. The persistence timer sends a probe every 60 seconds to prompt the receiver until a non-zero window is advertised.
*   **Time Out Timer (TOT):** Dynamically adjusts based on network traffic. The basic rule increases the timer if actual RTT increases (high traffic) and decreases it if actual RTT drops (low traffic).

## 4. Routing Instability (Count to Infinity)
*   **The Problem:** Distance Vector protocols (like RIP) react rapidly to good news but slowly to bad news. When a link fails, neighbors continue to exchange outdated routing tables, incrementally increasing the cost to the failed node until it reaches "infinity" (Count to Infinity).
*   **Solutions:**
    *   *Defining Infinity:* Setting a strict, low hop limit. RIP defines infinity as 16 hops, capping the maximum network size at 15 hops.
    *   *Split Horizon:* A router will never advertise a route back out the same interface it learned it from.
    *   *Poison Reverse / Route Poisoning:* When a link fails, the metric is immediately set to infinity to stop neighbors from using it.
*   **Hierarchical Routing (Link State):** For large networks, flat routing tables become too massive. Dividing networks into hierarchical regions (e.g., routers $\rightarrow$ regions $\rightarrow$ clusters) drastically reduces routing table sizes to approximately $\ln N$ entries.

## 5. DNS Architecture & Name Resolution
*   **Hierarchical Structure:** DNS is an inverted tree (up to 128 levels) that decentralizes naming.
*   **Resolution Process:** A Local DNS server queries a Root Server, which points to a Top-Level Domain (TLD) server (like .com or .in). The TLD server does not hold the final IP address; it holds the Authoritative Name Server mappings. The Local DNS then queries the Authoritative server to get the final Resource Record (RR) containing the IP address.

---

# High-Yield Numerical Problem Bank

*   **Pure ALOHA Throughput:** Given a 200 kbps channel, 200-bit frames, and a system load generating 1000 frames/sec. Frame transmission time ($T_t$) is $1 \text{ ms}$. At 1000 frames/sec, $G = 1 \text{ frame/ms}$. Throughput $= G \times e^{-2G} = 0.135$. Out of 1000 frames, exactly 135 will survive.
*   **Subnetting Calculation:** Dividing a 200.1.2.0/24 network into three subnets (128 hosts, 64 hosts, 64 hosts).
    *   *1st Subnet (128 hosts):* Needs 7 host bits ($2^7 = 128$). Range: .0 to .127. Subnet Mask: 255.255.255.128. Direct Broadcast Address (DBA): 200.1.2.127.
    *   *2nd Subnet (64 hosts):* Needs 6 host bits ($2^6 = 64$). Range: .128 to .191. Subnet Mask: 255.255.255.192. DBA: 200.1.2.191.
    *   *3rd Subnet (64 hosts):* Range: .192 to .255. Subnet Mask: 255.255.255.192. DBA: 200.1.2.255.
*   **CIDR Aggregation Validation:** Combining 128.56.24.0/24 through 128.56.27.0/24. Total blocks = $4$ (power of 2). Total addresses = $4 \times 2^8 = 2^{10}$. The starting address 128.56.24.0 has its 10 least significant bits as zero, making it perfectly divisible by $2^{10}$. The resulting supernet requires 22 bits for the network ID, yielding 128.56.24.0/22.

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