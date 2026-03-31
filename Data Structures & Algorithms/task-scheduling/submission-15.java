// MATH

// tried myself, wrong

class Solution {
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
            if(i == max) {
                
                max_count++;
            }
            sum+=i;
        }
        System.out.println(map.toString());
        System.out.println(max);
        System.out.println(max_count);
        System.out.println(sum);
        return Math.max(sum, ((n+1)*(max-1)) + max_count);
    }
}
