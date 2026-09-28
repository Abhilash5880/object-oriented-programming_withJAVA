# GATE Computer Networks Master Notes and Solutions

## 1. Mathematical Formulas & Core Concepts

### Data Link Layer & Access Protocols
* **Transmission Delay ($T_t$):** 
  $$T_t = \frac{L}{B}$$
  *(where $L$ is packet/frame size in bits, and $B$ is bandwidth in bits/second)*

* **Propagation Delay ($T_p$):** 
  $$T_p = \frac{d}{v}$$
  *(where $d$ is link distance and $v$ is propagation/signal speed)*

* **CSMA/CD Efficiency ($\eta$):** 
  $$\eta = \frac{1}{1 + 6.44a}$$
  *(where $a = \frac{T_p}{T_t}$)*

* **CSMA/CD Minimum Frame Size:** 
  $$L \ge B \times 2 \times T_p$$

* **Pure ALOHA Vulnerable Time:** $2 \times T_t$

* **Pure ALOHA Throughput ($S$):** 
  $$S = G \times e^{-2G}$$
  *(Maximum throughput is $1/(2e) \approx 0.184$ when offered load $G = 0.5$)*

* **Slotted ALOHA Vulnerable Time:** $T_t$

* **Slotted ALOHA Throughput ($S$):** 
  $$S = G \times e^{-G}$$
  *(Maximum throughput is $1/e \approx 0.368$ when offered load $G = 1$)*

### Sliding Window & Efficiency
* **Link Utilization / Efficiency ($\eta$):** 
  $$\eta = \frac{n}{1 + 2a}$$
  *(where $n$ is window size and $a = \frac{T_p}{T_t}$)*

### TCP Timers & Congestion Control
* **Effective Send Window Size:** 
  $$\text{Send Window} = \min(\text{rwnd}, \text{cwnd})$$
  *(where $\text{rwnd}$ is Receiver Window for flow control and $\text{cwnd}$ is Congestion Window for congestion control)*

* **Jacobson's Algorithm for Timeout Timer (TOT):**
  * Actual Deviation ($AD_n$): 
    $$AD_n = \vert{}IRTT_n - ARTT_n\vert{}$$
  * Next Deviation ($ID_{n+1}$): 
    $$ID_{n+1} = \alpha \cdot ID_n + (1 - \alpha) \cdot AD_n$$
  * Timeout Timer: 
    $$TOT = 4 \times ID + IRTT$$

---

## 2. Solved Numerical Problem Bank

### Problem 1: Pipelined Transmission Delay Across Multiple Routers
* **Scenario:** A file of size $10^6\text{ bits}$ is transmitted from Source $S$ to Destination $D$ over 3 links ($L_1, L_2, L_3$) and 2 routers ($R_1, R_2$). Each link is $100\text{ km}$, signal speed is $10^8\text{ m/s}$, bandwidth is $1\text{ Mbps}$, and the file is split into $1000\text{ packets}$ of $1000\text{ bits}$ each.
* **Step-by-Step Calculation:**
  * Propagation delay per link ($T_p$) = $\frac{10^5\text{ m}}{10^8\text{ m/s}} = 1\text{ ms}$
  * Transmission delay per packet ($T_t$) = $\frac{1000\text{ bits}}{10^6\text{ bps}} = 1\text{ ms}$
  * First packet time across 3 hops ($h = 3$) = $3 \times (T_t + T_p) = 3 \times (1\text{ ms} + 1\text{ ms}) = 6\text{ ms}$
  * Remaining packets time ($N - 1 = 999$) = $999 \times T_t = 999 \times 1\text{ ms} = 999\text{ ms}$
  * **Total Time:** $6\text{ ms} + 999\text{ ms} = \mathbf{1005\text{ ms}}$ *(Option A)*

### Problem 2: Distance Vector Routing Update
* **Scenario:** In a 5-node network ($N_1$ to $N_5$), the cost of link $N_2-N_3$ reduces from $6$ to $2$. 
* **Step-by-Step Calculation:**
  * Immediate incident node updates change $DV_{N3}$'s distance to $N_2$ to $2$.
  * During the next update round at node $N_3$, applying the Bellman-Ford equation across neighbors $N_2$ and $N_4$:
    $$D_{N3}(x) = \min \{ c(N3, N2) + DV_{N2}(x),\; c(N3, N4) + DV_{N4}(x) \}$$
  * **New Distance Vector at Node $N_3$:** $\mathbf{(3, 2, 0, 2, 5)}$ *(Option A)*

### Problem 3: NTP Time Synchronization & Clock Offset
* **Scenario:** Original timestamp $= 46\text{ ms}$, Receive timestamp $= 59\text{ ms}$, Transmit timestamp $= 60\text{ ms}$, Arrival timestamp $= 67\text{ ms}$.
* **Step-by-Step Calculation:**
  * Sending time = $59 - 46 = 13\text{ ms}$
  * Receiving time = $67 - 60 = 7\text{ ms}$
  * Round-Trip Time (RTT) = $13 + 7 = 20\text{ ms}$
  * One-Way Delay = $\frac{20}{2} = 10\text{ ms}$
  * Expected Receive Time = $46 + 10 = 56\text{ ms}$
  * Clock Difference = $59 - 56 = +3\text{ ms}$
  * **Result:** **Receive clock should go back by 3 milliseconds** *(Option A)*

### Problem 4: Pure ALOHA Throughput
* **Scenario:** A pure ALOHA network transmits $200\text{-bit}$ frames over a $200\text{ kbps}$ channel, generating a total system load of $500\text{ frames/second}$.
* **Step-by-Step Calculation:**
  * Frame transmission time ($T_f$) = $\frac{200\text{ bits}}{200 \times 10^3\text{ bps}} = 1\text{ ms}$
  * Offered load ($G$) = $500\text{ frames/sec} \times 10^{-3}\text{ sec} = 0.5$
  * Throughput ($S$) = $G \times e^{-2G} = 0.5 \times e^{-1} \approx \mathbf{0.184}$ *(Option B)*

### Problem 5: CIDR Block Address Range Verification
* **Scenario:** Organization is granted block $130.34.12.64/26$. 
* **Step-by-Step Calculation:**
  * Host bits = $32 - 26 = 6\text{ bits}$
  * Total addresses = $2^6 = 64$
  * Valid range = From $130.34.12.64$ to $130.34.12.127$
  * Any address exceeding $127$ (such as $130.34.12.132$) does not belong to this organization.

### Problem 6: Supernetting Bitwise AND Validation
* **Scenario:** Supernet first address is $205.16.32.0$ with mask $255.255.248.0$.
* **Step-by-Step Calculation:**
  * The third octet mask `248` in binary is `11111000`.
  * Testing packet address $205.16.39.44$: third octet `39` is `00100111`.
  * Bitwise AND: 
    $$00100111_2 \text{ AND } 11111000_2 = 00100000_2 = 32$$
  * Yields $205.16.32.0$, confirming it belongs to the supernet.

### Problem 7: IPv4 Header Length Validation
* **Scenario:** An IP packet arrives with the first 8 bits as `0100 0010`.
* **Step-by-Step Analysis:**
  * Version (First 4 bits) = `0100` = $4$ (IPv4).
  * Header Length / IHL (Next 4 bits) = `0010` = $2$.
  * Actual header length = $2 \times 4\text{ bytes} = 8\text{ bytes}$.
  * Since the minimum required IPv4 header length is $20\text{ bytes}$, the packet is malformed and **rejected by the receiver**.

---

## 3. Core Protocol Quick Reference
* **IGMP (Internet Group Management Protocol):** Manages multicast group memberships.
* **OSPF (Open Shortest Path First):** Interior Gateway Protocol (IGP) using Link-State routing within an Autonomous System.
* **BGP (Border Gateway Protocol):** Exterior Gateway Protocol (EGP) operating between Autonomous Systems.
* **RIP (Routing Information Protocol):** Classic Distance Vector routing protocol using hop count metric (max 15 hops).

### IPv6 Addressing Modes
* **Unicast:** Uniquely identifies a single interface (one-to-one).
* **Multicast:** Delivers packets to multiple member hosts simultaneously (one-to-many).
* **Anycast:** Assigns the exact same IP to multiple interfaces; routes to the closest interface.
* **Broadcast:** **Not supported** in IPv6 (replaced by multicast/anycast).