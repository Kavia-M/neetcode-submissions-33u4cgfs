// GREEDY

/*
The "first" task's gaps are the only ones that matter because any other task with the same frequency will always have its last occurrence placed at the very end of the schedule. Since there are no more tasks of that type left to schedule, that final occurrence never triggers a need for more idle time
.

if more than 1 letter has same frequency
eg. A A A   B B B   C C C    D D D 
n = 2

A _ _ A _ _ A
filling B:
A B _ A B _ A B
filling C :
A B C A B C A B C
filling D : this is where -ve filling of gaps
A B C D A B C D A B C D

here we also have trailing letters filled after the max
*/
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26]; // space O(26) => O(1) space
        int maxInd = -1;
        int maxf = Integer.MIN_VALUE;
        for(char task : tasks) { // time O(m) m tasks
            count[task-'A']++;
            if(count[task-'A'] > maxf) {
                maxf = count[task-'A'];
                maxInd = task-'A';
            }
        }

        int idle = n * (maxf-1);
        for(int i = 0; i<26; i++) { // time O(26) => O(1)
            if(i==maxInd) continue;
            // check others only
            idle -= Math.min(maxf-1, count[i]); // since maxf - 1 gapGroups only there, total gaps is different from gapGroups, in same gapGroup we cannot fill same letter more than once
        }
        
        return tasks.length + Math.max(idle, 0);
    }
}



