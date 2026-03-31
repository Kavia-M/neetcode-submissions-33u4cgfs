// GREEDY
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        int maxf = Integer.MIN_VALUE;
        for(char task : tasks) {
            count[task-'A']++;
            if(count[task-'A'] > maxf)
                maxf = count[task-'A'];
        }

        int idle = n * (maxf-1);
        int others = tasks.length - maxf;
        System.out.println(maxf);
        System.out.println(idle);
        System.out.println(others);
        idle -= Math.min(others, maxf - 1);

        return tasks.length + Math.max(idle, 0);
    }
}



