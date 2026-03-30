class Solution {
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        int max_count = 0;
        for(char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        for(int i : map.values()) {
            if(i > max) {
                max = i;
            }
            if(i == max) {
                
                max_count++;
            }
        }
        System.out.println(map.toString());
        System.out.println(max);
        System.out.println(max_count);
        return (n*max) + max_count - 1;
    }
}
