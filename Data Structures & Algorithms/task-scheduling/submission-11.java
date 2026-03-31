// HEAP SOLUTION
/*
EXPLANATION FROM NOTEBOOK LM
The **Task Scheduler** algorithm uses a **Greedy approach** with a **Max-Heap** and a **Queue** to ensure that tasks are completed in the shortest amount of time possible while respecting a mandatory cooldown period.

Here is the step-by-step breakdown of the algorithm and the logic behind each specific action:

### 1. Count Task Frequencies
The first step is to count how many times each unique task appears in the input.
*   **Why:** We need to know the "remaining count" for every task to determine which ones are the most urgent to schedule.

### 2. Build a Max-Heap of Counts
The algorithm stores these frequencies in a **Max-Heap**, where the task with the highest remaining count is always at the top.
*   **Why:** The intuition is that **tasks with the most remaining occurrences are the hardest to fit into the schedule** because they require the most cooldown gaps. By prioritizing them, we reduce the likelihood of being forced into "idle time" at the end of the process.

### 3. Initialize a Cooldown Queue
An empty **FIFO (First-In-First-Out) queue** is created to store pairs consisting of the `remaining_count` of a task and its `next_available_time`.
*   **Why:** When a task is run, it cannot be run again for $n$ units of time. The queue acts as a "waiting room," keeping these tasks out of the Max-Heap until their cooldown period has expired.

### 4. The Main Execution Loop
The algorithm enters a loop that continues as long as there are tasks in the heap (ready to run) or tasks in the queue (waiting for cooldown).
*   **Increment Time:** At each iteration, time is incremented by 1. This represents a single CPU slot.
*   **Process a Task:** If the Max-Heap is not empty, the algorithm pops the task with the highest frequency and "runs" it, which means **decrementing its remaining count by 1**.
*   **Move to Cooldown:** If that task still has occurrences left (count > 0), it is pushed into the **Cooldown Queue** with a timestamp of `current_time + n`.
    *   **Why:** This ensures the task is "locked" and cannot be picked up by the Max-Heap again until $n$ units of time have passed.

### 5. Re-enabling Tasks
After processing a slot, the algorithm checks the front of the Cooldown Queue to see if the `next_available_time` for the waiting task matches the current `time`.
*   **Why:** If the cooldown has expired, the task is **moved from the queue back into the Max-Heap**. This makes the task eligible to be scheduled by the CPU once again.

### Summary of Why This Works
This specific sequence ensures that the **CPU is always working on the most "difficult" (most frequent) available tasks first**. By using the queue to manage the cooldown, the algorithm naturally fills the required $n$ gaps with other tasks from the heap. If the heap is empty but tasks are still cooling down, the CPU simply waits (increments time) until a task becomes available again.

***

**Would you like me to generate a tailored report that summarizes this logic into a study guide, or perhaps create a quiz to test your understanding of how the Max-Heap and Queue interact?** A **video overview** could also help visualize how tasks move between the heap and the queue.

*/

/*

For your implementation (Re-enabling at the Start):
// Re-enabling at the start works because 'index + n + 1' targets the exact future slot.
// By checking first, the task is moved to the heap and becomes immediately 
// eligible for selection in the current CPU cycle while still respecting the n-gap.
For the standard implementation (Re-enabling at the End):
// Re-enabling at the end ensures the task is ready only for the NEXT iteration. [1]
// Using 'time + n' means the task remains in the queue during the current slot,
// preventing it from being picked too early and violating the cooldown period. [1]
Key Distinction: Your version calculates the exact time of availability to pick it now, whereas the standard NeetCode approach
 calculates the expiration of the cooldown to pick it later. Both effectively maintain the mandatory n gap between identical tasks.


*/
class Solution_reenabling_at_start {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];

        for(char task : tasks) {
            count[task - 'A']++;
        }

        // in max_heap or q we no need to keep task name or number
        // ready processes remaining time
        PriorityQueue<Integer> ready_processes = new PriorityQueue<>(Comparator.reverseOrder());
        int index;
        // waiting list remaining time and next index it will be available
        Queue<int[]> waiting_list = new ArrayDeque<>(); // since we do nto add null array DQ

        for(int i=0; i<26; i++) {
            if(count[i]>0) {
                ready_processes.offer(count[i]);
            }
        } 

        for(index = 0; !ready_processes.isEmpty() || !waiting_list.isEmpty(); index++) { // index is updated +1 at last after all are processed
            // updating current ready processes
            if(!waiting_list.isEmpty() && waiting_list.peek()[1] == index) {
                ready_processes.offer(waiting_list.poll()[0]);
            }

            if(ready_processes.isEmpty()) continue;
            // select the 1st max process for the current index
            int current_selected_process_remaining_time = ready_processes.poll();
            current_selected_process_remaining_time--;

            // after each ready process gets a slot in CPU, it is mandatory added to WL q
            if(current_selected_process_remaining_time>0)
                waiting_list.offer(new int[]{current_selected_process_remaining_time, index + n + 1}); // +n is waiting time. available time is 1 AFTER waiting time

        } 
        return index;
    }
}

// here next_available_time in q [1] is actually that process Waiting time ending time
class Solution_maxHeap_Q {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];

        for(char task : tasks) {
            count[task - 'A']++;
        }

        // in max_heap or q we no need to keep task name or number
        // ready processes remaining time
        PriorityQueue<Integer> ready_processes = new PriorityQueue<>(Collections.reverseOrder());
        int time = 0;
        // waiting list remaining time and next available time (AFTER THIS TIME it will be available)
        Queue<int[]> waiting_list = new ArrayDeque<>(); // since we do not add null array DQ

        for(int i=0; i<26; i++) {
            if(count[i]>0) {
                ready_processes.offer(count[i]);
            }
        } 

        while(!ready_processes.isEmpty() || !waiting_list.isEmpty()) {
            time++;
            if(!ready_processes.isEmpty()) {
                // select the 1st max process for the current time
                int current_selected_process_remaining_time = ready_processes.poll();
                current_selected_process_remaining_time--;

                // after each ready process gets a slot in CPU, it is mandatory added to WL q
                if(current_selected_process_remaining_time > 0)
                waiting_list.offer(new int[]{current_selected_process_remaining_time, time + n});
            }
            // the below process is at the end of the waiting time
            if(!waiting_list.isEmpty() && waiting_list.peek()[1] == time) {
                ready_processes.offer(waiting_list.poll()[0]);
                // this will be in ready_processes while we do next iteration
            }
        } 
        return time;
    }
}

// OPTIMISED
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];

        for(char task : tasks) {
            count[task - 'A']++;
        }

        // in max_heap or q we no need to keep task name or number
        // ready processes remaining time
        PriorityQueue<Integer> ready_processes = new PriorityQueue<>(Collections.reverseOrder());
        int time = 0;
        // waiting list remaining time and next available time (AFTER THIS TIME it will be available)
        Queue<int[]> waiting_list = new ArrayDeque<>(); // since we do not add null array DQ

        for(int i=0; i<26; i++) {
            if(count[i]>0) {
                ready_processes.offer(count[i]);
            }
        } 

        while(!ready_processes.isEmpty() || !waiting_list.isEmpty()) {
            time++; // idle and time++ only until we get something in ready processes
            if(ready_processes.isEmpty()) {
                // next process to be processed is the one in WL q
                // until then idle, we can directly make time to the WAITING_TIME_ENDING of the q front.
                // it will be == time when checked in below code if stmt
                // added to ready_processes and in next iteration it will be processes
                time = waiting_list.peek()[1];
            }
            else { // if(!ready_processes.isEmpty()) {
                // select the 1st max process for the current time
                int current_selected_process_remaining_time = ready_processes.poll();
                current_selected_process_remaining_time--;

                // after each ready process gets a slot in CPU, it is mandatory added to WL q
                if(current_selected_process_remaining_time > 0)
                    waiting_list.offer(new int[]{current_selected_process_remaining_time, time + n});
            }
            // the below process is at the end of the waiting time
            if(!waiting_list.isEmpty() && waiting_list.peek()[1] == time) {
                ready_processes.offer(waiting_list.poll()[0]);
                // this will be in ready_processes while we do next iteration
            }
        } 
        return time;
    }
}



