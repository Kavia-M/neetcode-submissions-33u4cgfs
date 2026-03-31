// MATH

// tried myself, wrong
// WHAT mistake i did earlier was, i updated the max_count in the loop of updating max itself, if max is not finalized it will mislead the max count as each time max updated max count also updated
class Solution_MATH {
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        int max_count = 0;
        for(char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        int sum = 0;
        for(int i : map.values()) {
            if(i > max) {
                max = i;
            }
        }
        for(int i : map.values()) {
            if(i == max) {
                
                max_count++;
            }
            sum+=i;
        }
        System.out.println(map.toString());
        System.out.println(max);
        System.out.println(max_count);
        System.out.println(sum);
        System.out.println(tasks.length);
        return Math.max(sum, ((n+1)*(max-1)) + max_count);
    }
}

// GIVEN MATH solution
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        // max can be updated in loop itself, or we can use stream also as in GIVEN solution
        for(char task : tasks) {
            count[task - 'A']++;
        }
        
        // Arrays.stream(count) Convert int[] to IntStream
        // .max() Find maximum frequency
        // max() returns OptionalInt
        // getAsInt() extracts the value
        int maxf = Arrays.stream(count).max().getAsInt();

        // if array is empty NoSuchElementException raised by getAsInt()
        // we can also use max().orElse(0) if we doubt array can be empty


        // Find maximum frequency
        // Filter elements equal to max frequency
        // count() returns long so cast if needed
        // Gives number of tasks with max frequency
        int maxCount = (int) Arrays.stream(count)
                           .filter(c -> c == maxf)
                           .count();
        
        int time = ((maxf-1)*(n+1)) + maxCount; // after maxf-1 groups
        // each maxf-1 group has n+1 -> 1 element followed by n gaps.
        // if we have elements that == maxCount, it wil be trailing after maxf-1 sets
        // if we have more elemets other than elements with maxF, other elements with less frequency can be fit additionally in the maxf-1 groups we already have, this will leave no more gaps so answer would be lenght of the given tasks itself
        /*
            A A A B B B C D E F G H
            n=2

            A _ _ A _ _ ==> maxf-1 sets
            A B _ A B _ A B ==> trainling after the sets, 1 occurence of each element with maxf freq. so + maxCount
            A B C A B D A B ==> gaps filled (this step is skipped by this algo)
            A B C E A B D F A B ==> inbetween we fitted it, no need trailling (continuation of above step, this also skipped in algo)
            A B C E G A B D F H A B ==> continuation of above step, here all tasks done. no gaps. means answer is tasks.length only

            by following above method of stuffing the new letters in the maxf-1 groups itself we effectively manage the n cooldowns without trailing and without new gaps

        */

        return Math.max(tasks.length, time);
    }
}